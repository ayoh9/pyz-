package io.ayobami.financeapptenant.service.dataProcessing;

import io.ayobami.financeapptenant.config.TenantContextHolder;
import io.ayobami.financeapptenant.entity.ResponseBody;
import io.ayobami.financeapptenant.entity.dataProcessor.DataProcessorRequestDTO;
import io.ayobami.financeapptenant.entity.dataProcessor.ProcessedDataEntity;
import io.ayobami.financeapptenant.entity.dataProcessor.apiRequestDetails.TenantApiRequestDetails;
import io.ayobami.financeapptenant.repository.ProcessedDataRepository;
import io.ayobami.financeapptenant.service.apiCallHandler.ApiCaller;
import io.ayobami.financeapptenant.entity.dataProcessor.DataProcessorResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;

@Service
@AllArgsConstructor
@Slf4j
public class DataProcessorServiceImpl implements DataProcessorService{

    private final ApiCaller apiCaller;
    private final ProcessedDataRepository processedDataRepository;

    @Override
    @Transactional
    public ResponseBody processData(TenantApiRequestDetails tenantApiRequestDetails) {
        try {
            // set tenant context
            TenantContextHolder.setTenantId(tenantApiRequestDetails.getTenantInstance());

            // call client api
            DataProcessorRequestDTO dataProcessorRequestDTO = apiCaller.callTenantApi(tenantApiRequestDetails);

            if (dataProcessorRequestDTO == null)
                return ResponseBody.builder()
                        .statusCode(500)
                        .message("Unable to reach core service")
                        .build();

            // process data
            ProcessedDataEntity processedData = ProcessedDataEntity.builder()
                    // body of processed data entity
                    .build();

            // save processed data in db
            processedDataRepository.save(processedData);

            // build dto for processed data
            DataProcessorResponseDTO responseDto = DataProcessorResponseDTO.builder()
                    // body of processed data dto
                    .build();

            return ResponseBody.builder()
                    .statusCode(200)
                    .message("Success")
                    .data(responseDto)
                    .build();
        } catch (Exception e) {
            log.info("MESSAGE: {}", e.getMessage());
            log.info("StackTrace: {}", Arrays.toString(e.getStackTrace()));
            return ResponseBody.builder()
                    .statusCode(500)
                    .message("Failure. Contact system administrator")
                    .build();
        }
    }
}
