package io.ayobami.financeapptenant.entity.tenant;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DataSourceDTO {

    int id;

    String tenantId;

    String url;

    String username;

    String password;

    String driverClassName;
}
