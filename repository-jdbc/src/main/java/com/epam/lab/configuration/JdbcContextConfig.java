package com.epam.lab.configuration;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:database.properties")
public class JdbcContextConfig {

    @Autowired
    Environment environment;

    private static final String URL = "url";
    private static final String USER = "dbUser";
    private static final String DRIVER = "driver";
    private static final String PASSWORD = "dbPassword";

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(environment.getProperty(URL));
        config.setUsername(environment.getProperty(USER));
        config.setPassword(environment.getProperty(PASSWORD));
        config.setDriverClassName(environment.getProperty(DRIVER));
        return new HikariDataSource(config);
    }
}
