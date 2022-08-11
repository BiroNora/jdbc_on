package com.norab.backstage.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration("userdb")
public class UserDataSourceConfig {
    @Bean("userdbsource")
    @ConfigurationProperties("app.datasource.users")
    public HikariDataSource hikariDataSource() {
        return DataSourceBuilder
            .create()
            .type(HikariDataSource.class)
            .build();
    }

    @Bean("userJdbcTemplate")
    public JdbcTemplate userJdbcTemplate(@Qualifier("userdbsource") HikariDataSource hikariDataSource) {

        return new JdbcTemplate(hikariDataSource);
    }
}
