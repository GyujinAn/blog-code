package com.example.jpa.config;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("dev")
public class DevProfileConfigTest {

    @Value("${spring.datasource.url}")
    private String url;

    @Autowired
    private DataSource dataSource;

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
