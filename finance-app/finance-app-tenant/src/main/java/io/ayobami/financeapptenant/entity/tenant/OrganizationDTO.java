package io.ayobami.financeapptenant.entity.tenant;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class OrganizationDTO {

    private String email;

    private String orgName;

    private String firstName;

    private String lastName;

    private String instanceName;
}
