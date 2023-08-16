package com.example.jpa.cashing;

import com.example.jpa.Account;
import com.example.jpa.AccountRepository;
import com.example.jpa.Organization;
import com.example.jpa.OrganizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("postgresql")
class JpaCashingTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    void init() {
        accountRepository.deleteAll();
        organizationRepository.deleteAll();
    }

    @Transactional
    @Test
    void send_sql_statement_to_DB_after_updating_in_cash() {
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
        System.out.println("the sql statement should be sent to DB");
        List<Account> cList = accountRepository.findAllByOrganizationId(aOrganization.id);
        List<Account> dList = accountRepository.findAllByOrganizationId(bOrganization.id);
        System.out.println("end");

        // then
        assertNotEquals(aList, cList);
        assertNotEquals(bList, dList);
        bList.addAll(aList);
        assertEquals(new HashSet<>(dList), new HashSet<>(bList));
    }

    @Transactional
    @Test
    public void override_for_duplicated_pk() {
        // given
        UUID duplicatedPk = UUID.randomUUID();
        Account orignalAccount = new Account(duplicatedPk, UUID.randomUUID(), "hello");
        accountRepository.save(orignalAccount);
        entityManager.flush();
        entityManager.clear();

        // when
        Account account = new Account(duplicatedPk, UUID.randomUUID(), "world");
        accountRepository.save(account);
        System.out.println("the sql statement which update account pk1 should be sent");
        entityManager.flush();
        entityManager.clear();
        System.out.println();

        // then
        List<Account> accounts = accountRepository.findAll();
        assertEquals(1, accounts.size());
        assertEquals("world", accounts.get(0).name);
    }

    @Transactional
    @Test
    public void not_use_cashing_when_find_by_column_not_pk() {
        // given
        UUID pk = UUID.randomUUID();
        String nameNotPk = "hello";
        Account orignalAccount = new Account(pk, UUID.randomUUID(), nameNotPk);
        accountRepository.save(orignalAccount);
        entityManager.flush();
        entityManager.clear();
        Account account1 = accountRepository.findById(pk).get();

        // when
        System.out.println("the sql statement should be sent, not using cashing");
        Account account2 = accountRepository.findByName(nameNotPk).get();
        System.out.println("because the nameNotPk argument is not pk of account table");

        // then
    }

    @Transactional
    @Test
    public void not_use_cashing_when_using_jpql() {
        // given
        UUID pk = UUID.randomUUID();
        Account orignalAccount = new Account(pk, UUID.randomUUID(), "name");
        accountRepository.save(orignalAccount);
        entityManager.flush();
        entityManager.clear();
        Account account1 = accountRepository.findById(pk).get();

        // when
        System.out.println("the sql statement should be sent, not using cashing");
        Account account2 = accountRepository.findByIdJpql(pk).get();
        System.out.println("because the findByIdJpql is JPQL query");
        // refer to https://docs.jboss.org/hibernate/orm/5.6/userguide/html_single/Hibernate_User_Guide.html#fetching-direct-vs-query

        // then
    }


}
