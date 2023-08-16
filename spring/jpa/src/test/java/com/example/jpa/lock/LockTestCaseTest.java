package com.example.jpa.lock;

import com.example.jpa.Account;
import com.example.jpa.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("postgresql")
@SpringBootTest
class LockTestCaseTest {

    @Autowired
    LockTestCase lockTestCase;

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    void init() {
        accountRepository.deleteAll();
    }

    @Test
    public void testCannotAcquireLockException() {
        // given
        UUID accountId = UUID.randomUUID();
        accountRepository.save(new Account(accountId, null, "before updating"));

        // when
        new Thread(() -> {
            try {
                lockTestCase.testCannotAcquireLockException(accountId, "first tx", true, false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        // then
        assertThrows(CannotAcquireLockException.class, () -> {
            lockTestCase.testCannotAcquireLockException(accountId, "second tx", false, true);
        });
        Account account = accountRepository.findById(accountId).get();
        assertEquals("first tx", account.name);
    }

    @Test
    public void testSecondTxWaiteLockOfFirstTx() throws InterruptedException {
        // given
        UUID accountId = UUID.randomUUID();
        accountRepository.save(new Account(accountId, null, "before updating"));

        // when
        new Thread(() -> {
            try {
                lockTestCase.testSecondTxWaiteLockFromFirstTx(accountId, "first tx", true, false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        lockTestCase.testSecondTxWaiteLockFromFirstTx(accountId, "second tx", false, true);

        // then
        Account account = accountRepository.findById(accountId).get();
        assertEquals("second tx", account.name);
    }

    @Test
    public void testLostUpdate_when_isolation_level_REPEATABLE_READ(){
        // given
        UUID accountId = UUID.randomUUID();
        accountRepository.save(new Account(accountId, null, "before updating", "before updating"));

        // when
        new Thread(() -> {
            try {
                lockTestCase.testLostUpdateOfREPEATABLE_READ(accountId, true, false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        // then
        assertThrows(CannotAcquireLockException.class, () -> {
            lockTestCase.testLostUpdateOfREPEATABLE_READ(accountId, false, true);
        });
        Account account = accountRepository.findById(accountId).get();
        assertEquals("nameToUpdate", account.name);
        assertEquals("before updating", account.address);
    }

    @Test
    public void testLostUpdate_when_isolation_level_READ_COMMITTED() throws InterruptedException {
        // given
        UUID accountId = UUID.randomUUID();
        accountRepository.save(new Account(accountId, null, "before updating", "before updating"));

        // when
        new Thread(() -> {
            try {
                lockTestCase.testLostUpdateOfREAD_COMMITTED(accountId, true, false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
        lockTestCase.testLostUpdateOfREAD_COMMITTED(accountId, false, true);

        // then
        Account account = accountRepository.findById(accountId).get();
        assertEquals("before updating", account.name);
        assertEquals("addressToUpdate", account.address);
    }

}
