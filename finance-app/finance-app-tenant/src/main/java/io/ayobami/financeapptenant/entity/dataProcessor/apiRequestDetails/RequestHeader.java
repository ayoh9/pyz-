package io.ayobami.financeapptenant.entity.dataProcessor.apiRequestDetails;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class RequestHeader {

    private String headerKey;
    private String headerValue;
}
