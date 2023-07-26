package com.example.jpa.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("h2-datasource")
public class H2DataSourceProfileTest {
    @Autowired
    private DataSource dataSource;

    @Value("${h2-datasource.url}")
    private String url;

    @Value("${h2-datasource.username}")
    private String username;

    @Value("${h2-datasource.password}")
    private String password;

    @Test
    public void should_be_h2_data_source() {
        // given

        // when
        DriverManagerDataSource driverManagerDataSource = (DriverManagerDataSource) dataSource;
        String urlOfDataSource = driverManagerDataSource.getUrl();
        String usernameOfDataSource = driverManagerDataSource.getUsername();
        String passwordOfDataSource = driverManagerDataSource.getPassword();

        // then
        assertEquals(url, urlOfDataSource);
        assertEquals(username, usernameOfDataSource);
        assertEquals(password, passwordOfDataSource);
    }
}
