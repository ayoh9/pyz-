package io.ayobami.financeapptenant.service.tenant;

import com.zaxxer.hikari.HikariDataSource;
import io.ayobami.financeapptenant.config.MultiTenancyJpaConfiguration;
import io.ayobami.financeapptenant.config.TenantContextHolder;
import io.ayobami.financeapptenant.entity.ResponseBody;
import io.ayobami.financeapptenant.entity.tenant.DataSourceDTO;
import io.ayobami.financeapptenant.entity.tenant.OrganizationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final RestTemplate restTemplate;

    private final Map<String, DataSource> dataSourcesMtApp;

    @Override
    public ResponseBody register(OrganizationDTO orgDTO) {
        boolean flag = false;
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<DataSourceDTO[]> response = restTemplate.exchange("http://localhost:2020/core/getAll", HttpMethod.GET,
                new HttpEntity<>(headers), DataSourceDTO[].class);

        if (response.getBody() != null) {
            DataSourceDTO[] dsList = response.getBody();
            if (dsList != null) {
                for (DataSourceDTO d : dsList) {
                    if (d.getTenantId().equalsIgnoreCase(orgDTO.getInstanceName())) {
                        flag = true;
                        break;
                    }
                }
            }
        }

        if (flag) {
            return buildErrorResponse(400, "Tenant already exists!");
        }

        String url = "http://localhost:2020/core/addsource/" + orgDTO.getInstanceName();
        try {
            restTemplate.postForEntity(url, orgDTO, String.class);
        } catch (Exception e) {
            log.info("MESSAGE: {}", e.getMessage());
            log.info("StackTrace: {}", Arrays.toString(e.getStackTrace()));
            return buildErrorResponse(500, "Error registering tenant");
        }

        DataSourceBuilder<?> factory = DataSourceBuilder.create(MultiTenancyJpaConfiguration.class.getClassLoader())
                .url("jdbc:mysql://localhost:3306/" + orgDTO.getInstanceName() + "?useSSL=false").username("admin")
                .password("admin").driverClassName("com.mysql.cj.jdbc.Driver");
        HikariDataSource ds = (HikariDataSource) factory.build();
        ds.setKeepaliveTime(40000);
        ds.setMinimumIdle(1);
        ds.setMaxLifetime(45000);
        ds.setIdleTimeout(35000);
        dataSourcesMtApp.put(orgDTO.getInstanceName(), ds);
        TenantContextHolder.setTenantId(orgDTO.getInstanceName());

        return ResponseBody.builder()
                .statusCode(200)
                .message("Tenant registered successfully!")
                .data(orgDTO.getInstanceName())
                .build();
    }

    private ResponseBody buildErrorResponse(int statusCode, String message) {
        return ResponseBody.builder()
                .statusCode(statusCode)
                .message(message)
                .build();
    }
}
