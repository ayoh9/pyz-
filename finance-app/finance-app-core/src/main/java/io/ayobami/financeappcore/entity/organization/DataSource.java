package io.ayobami.financeappcore.entity.organization;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name="datasource")
public class DataSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="tenantId")
    private String tenantId;

    @Column(name="url")
    private String url;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="driverClassName")
    private String driverClassName;
}
