package io.ayobami.financeapptenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@EnableJpaRepositories("io.ayobami")
public class FinanceAppTenantApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceAppTenantApplication.class, args);
	}

}
