package com.example.jpa.config;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class DefaultDataSourceConfigTest {

    @Autowired
    private DataSource dataSource;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    @Test
    public void should_be_postgres_data_source() {
        // given

        // when
        HikariDataSource driverManagerDataSource = (HikariDataSource) dataSource;
        String urlOfDataSource = driverManagerDataSource.getJdbcUrl();
        String usernameOfDataSource = driverManagerDataSource.getUsername();
        String passwordOfDataSource = driverManagerDataSource.getPassword();

        // then
        assertEquals(url, urlOfDataSource);
        assertNull(usernameOfDataSource);
        assertNull(passwordOfDataSource);
    }
}
