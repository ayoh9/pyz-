package io.ayobami.financeapptenant.service.dataProcessing;

import io.ayobami.financeapptenant.entity.ResponseBody;
import io.ayobami.financeapptenant.entity.dataProcessor.apiRequestDetails.TenantApiRequestDetails;

public interface DataProcessorService {

    ResponseBody processData(TenantApiRequestDetails tenantApiRequestDetails);
}
