package io.ayobami.financeapptenant.web;

import io.ayobami.financeapptenant.entity.ResponseBody;
import io.ayobami.financeapptenant.entity.tenant.OrganizationDTO;
import io.ayobami.financeapptenant.entity.dataProcessor.apiRequestDetails.TenantApiRequestDetails;
import io.ayobami.financeapptenant.service.tenant.ClientService;
import io.ayobami.financeapptenant.service.dataProcessing.DataProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final DataProcessorService dataProcessorService;

    @PostMapping("/registerOrganization")
    public ResponseEntity<ResponseBody> registerOrg(@RequestBody OrganizationDTO orgDTO)
    {
        ResponseBody response = clientService.register(orgDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/processData")
    public ResponseEntity<ResponseBody> processData(@RequestBody TenantApiRequestDetails tenantApiRequestDetails) {
        ResponseBody response = dataProcessorService.processData(tenantApiRequestDetails);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
