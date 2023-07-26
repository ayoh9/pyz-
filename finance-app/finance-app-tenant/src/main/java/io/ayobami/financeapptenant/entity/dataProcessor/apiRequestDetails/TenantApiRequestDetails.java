package io.ayobami.financeapptenant.entity.dataProcessor.apiRequestDetails;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class TenantApiRequestDetails {

    private String tenantInstance;
    private String url;
    private List<RequestHeader> headers;
    private String requestBody;
}
