package com.norab.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfigurationTest {

    /*@Configuration
    public static class DataSourceConfig {
        @Bean
        @Primary
        @ConfigurationProperties(prefix = "application-test")
        public HikariDataSource hikariDataSource() {
            return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
        }

        @Bean
        public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource) {

            return new JdbcTemplate(hikariDataSource);
        }
    }*/
}
