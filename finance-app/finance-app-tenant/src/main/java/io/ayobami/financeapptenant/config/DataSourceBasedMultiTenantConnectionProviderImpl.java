package io.ayobami.financeapptenant.config;

import lombok.RequiredArgsConstructor;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Map;

@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl
extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Map<String, DataSource> dataSourcesMultitenantApp;

	@Override
	protected DataSource selectAnyDataSource() {
		return this.dataSourcesMultitenantApp.values().iterator().next();
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		return this.dataSourcesMultitenantApp.get(tenantIdentifier);
	}
}
