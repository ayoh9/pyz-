package io.ayobami.financeappcore.service.organization;

import io.ayobami.financeappcore.entity.organization.DataSource;
import io.ayobami.financeappcore.entity.organization.OrganizationDTO;
import io.ayobami.financeappcore.entity.organization.OrganizationEntity;
import io.ayobami.financeappcore.entity.ResponseBody;
import io.ayobami.financeappcore.repository.DataSourceRepository;
import io.ayobami.financeappcore.repository.OrganizationEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrganizationManagerServiceImpl implements CoreService {

    private final Flyway flyway;
    private final DataSourceRepository dataSourceRepository;
    private final OrganizationEntityRepository orgEntityRepository;

    @Transactional
    public ResponseBody registerTenantDataSource(OrganizationDTO orgDTO, String tenant) {
        try {
            DataSource dataSource = DataSource.builder()
                    .tenantId(tenant)
                    .username("admin")
                    .password("admin")
                    .url("jdbc:mysql://localhost:3306/"+tenant+"?useSSL=false")
                    .driverClassName("com.mysql.cj.jdbc.Driver")
            .build();

            dataSourceRepository.save(dataSource);

            OrganizationEntity orgEntity = OrganizationEntity.builder()
                    .email(orgDTO.getEmail())
                    .orgName(orgDTO.getOrgName())
                    .firstName(orgDTO.getFirstName())
                    .lastName(orgDTO.getLastName())
                    .instanceName(orgDTO.getInstanceName())
            .build();

            orgEntityRepository.save(orgEntity);

            Flyway fly = Flyway.configure()
                    .configuration(flyway.getConfiguration())
                    .schemas(tenant)
                    .defaultSchema(tenant)
                    .load();

            fly.migrate();

            return ResponseBody.builder()
                    .statusCode(200)
                    .message("Success. Tenant registered")
                    .build();
        } catch (Exception e) {
            log.info("\nTenant datasource registration failed. Cause: {}", e.getMessage());
            log.info("\nStack trace -> {}\n", Arrays.toString(e.getStackTrace()));

            return ResponseBody.builder()
                    .statusCode(500)
                    .message("Failure. Contact system administrator")
                    .build();
        }
    }

    public List<DataSource> getAllTenants() {
        return dataSourceRepository.findAll();
    }
}
