package com.example.jpa.config;

import com.example.jpa.Account;
import com.example.jpa.AccountRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class TestProfileConfigTest {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DataSource dataSource;

    @Test
    public void should_be_h2_data_source() {
        // given

        // when
        HikariDataSource driverManagerDataSource = (HikariDataSource) dataSource;
        String urlOfDataSource = driverManagerDataSource.getJdbcUrl();
        String usernameOfDataSource = driverManagerDataSource.getUsername();
        String passwordOfDataSource = driverManagerDataSource.getPassword();

        // then
        assertEquals(url, urlOfDataSource);
        assertEquals(username, usernameOfDataSource);
        assertEquals(password, passwordOfDataSource);
    }

    @Test
    public void save_data_to_H2() {
        // given
        Account account = new Account();

        // when
        accountRepository.save(account);

        // then
    }
}
