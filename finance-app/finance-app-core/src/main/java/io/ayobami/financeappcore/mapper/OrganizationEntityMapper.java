package io.ayobami.financeappcore.mapper;

import io.ayobami.financeappcore.entity.organization.OrganizationEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganizationEntityMapper implements RowMapper<OrganizationEntity> {

    public OrganizationEntity mapRow(ResultSet rs, int rowNum) throws SQLException {

        return OrganizationEntity.builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .orgName(rs.getString("orgName"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .instanceName(rs.getString("instanceName"))
                .build();
    }
}