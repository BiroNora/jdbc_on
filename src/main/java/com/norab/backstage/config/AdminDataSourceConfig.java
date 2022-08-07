package com.norab.backstage.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

public class AdminDataSourceConfig {
    @Bean
    @ConfigurationProperties("app.datasource.admin")
    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder
            .create()
            .type(HikariDataSource.class)
            .build();
    }

    @Bean(name = "admindb")
    public JdbcTemplate adminJdbcTemplate(HikariDataSource hikariDataSource) {

        return new JdbcTemplate(hikariDataSource);
    }
}
