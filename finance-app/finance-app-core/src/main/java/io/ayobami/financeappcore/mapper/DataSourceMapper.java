package io.ayobami.financeappcore.mapper;

import io.ayobami.financeappcore.entity.organization.DataSource;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSourceMapper implements RowMapper<DataSource> {

    public DataSource mapRow(ResultSet rs, int rowNum) throws SQLException {

        return DataSource.builder()
                .id(rs.getInt("id"))
                .tenantId(rs.getString("tenantId"))
                .url(rs.getString("url"))
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .driverClassName(rs.getString("driverClassName"))
                .build();
    }
}
