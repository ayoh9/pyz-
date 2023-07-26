package io.ayobami.financeapptenant.service.apiCallHandler;

import io.ayobami.financeapptenant.entity.dataProcessor.DataProcessorRequestDTO;
import io.ayobami.financeapptenant.entity.dataProcessor.apiRequestDetails.RequestHeader;
import io.ayobami.financeapptenant.entity.dataProcessor.apiRequestDetails.TenantApiRequestDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ApiCallerImpl implements ApiCaller {

    private final WebClient.Builder webClientBuilder;

    @Override
    public DataProcessorRequestDTO callTenantApi(TenantApiRequestDetails tenantApiRequest) {
        try {
            MultiValueMap<String, String> customHeaders = createRequestHeaders(tenantApiRequest.getHeaders());

            return webClientBuilder.build()
                    .post()
                    .uri(tenantApiRequest.getUrl())
                    .headers(httpHeaders -> httpHeaders.addAll(customHeaders))
                    .bodyValue(tenantApiRequest.getRequestBody())
                    .retrieve()
                    .bodyToMono(DataProcessorRequestDTO.class)
                    .block();
        } catch (Exception e) {
            log.info("MESSAGE: {}", e.getMessage());
            log.info("StackTrace: {}", Arrays.toString(e.getStackTrace()));
            return DataProcessorRequestDTO.builder().build();
        }
    }

    private MultiValueMap<String, String> createRequestHeaders(List<RequestHeader> headers) {
        MultiValueMap<String, String> customHeaders = new LinkedMultiValueMap<>();
        headers.forEach(header -> customHeaders.add(header.getHeaderKey(), header.getHeaderValue()));
        return customHeaders;
    }
}
