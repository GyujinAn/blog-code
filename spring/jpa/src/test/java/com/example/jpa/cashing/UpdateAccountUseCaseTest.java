package com.example.jpa.cashing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UpdateAccountUseCaseTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Transactional
    @Test
    void after_updating_in_cash_read_sql_send_again() {
        // given
        Organization aOrganization = organizationRepository.save(
                new Organization()
        );

        Organization bOrganization = organizationRepository.save(
                new Organization()
        );

        for (int i = 0; i < 10; i++) {
            Account account = new Account();
            account.organizationId = aOrganization.id;
            accountRepository.save(account);
        }

        for (int i = 0; i < 10; i++) {
            Account account = new Account();
            account.organizationId = bOrganization.id;
            accountRepository.save(account);
        }

        List<Account> aList = accountRepository.findAllByOrganizationId(aOrganization.id);
        List<Account> bList = accountRepository.findAllByOrganizationId(bOrganization.id);

        for (Account account : aList) {
            account.organizationId = bOrganization.id;
        }

        // when
        System.out.println("start");
        // the sql statement should be sent to DB
        List<Account> cList = accountRepository.findAllByOrganizationId(aOrganization.id);
        List<Account> dList = accountRepository.findAllByOrganizationId(bOrganization.id);
        System.out.println("end");

        // then
        assertNotEquals(aList, cList);
        assertNotEquals(bList, dList);
        bList.addAll(aList);
        assertEquals(dList, bList);
    }
}
