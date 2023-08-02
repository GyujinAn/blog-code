package com.example.jpa.feature;

import com.example.jpa.Account;
import com.example.jpa.AccountRepository;
import com.example.jpa.OrganizationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class FeatureTest {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Test
    public void deleteAll() {
        // given
        for (int i = 0; i < 10; i++) {
            Account account = new Account();
            accountRepository.save(account);
        }

        // when
        System.out.println("the sql statement which delete account should be sent");
        accountRepository.deleteAll();
        System.out.println();

        // then
        assertEquals(0, accountRepository.count());
    }
}
