package io.ayobami.financeapptenant.service.apiCallHandler;

import io.ayobami.financeapptenant.entity.dataProcessor.DataProcessorRequestDTO;
import io.ayobami.financeapptenant.entity.dataProcessor.apiRequestDetails.TenantApiRequestDetails;

public interface ApiCaller {

    DataProcessorRequestDTO callTenantApi(TenantApiRequestDetails request);
}
