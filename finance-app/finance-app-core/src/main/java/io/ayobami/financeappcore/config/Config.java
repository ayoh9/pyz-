package io.ayobami.financeappcore.config;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {};
    }
}
