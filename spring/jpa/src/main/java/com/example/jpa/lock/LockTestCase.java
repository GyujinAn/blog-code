package com.example.jpa.lock;

import com.example.jpa.Account;
import com.example.jpa.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.UUID;


@RequiredArgsConstructor
@Component
public class LockTestCase {

    private final AccountRepository accountRepository;

    private final EntityManager em;

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void testCannotAcquireLockException(UUID accountId, String nameToUpdate, boolean isFirstTx, boolean isSecondTx) throws InterruptedException {
        Account account = accountRepository.findById(accountId).get();
        System.out.println("isFirstTx: " + isFirstTx + ", isSecondTx: " + isSecondTx + ", name: " + account.name);
        if (isFirstTx) {
            account.name = nameToUpdate;
            em.flush();
            Thread.sleep(3000);
        }

        if (isSecondTx) {
            Thread.sleep(1000);
            account.name = nameToUpdate;
        }
    }

    @Transactional
    public void testSecondTxWaiteLockFromFirstTx(UUID accountId, String nameToUpdate, boolean isFirstTx, boolean isSecondTx) throws InterruptedException {
        Account account = accountRepository.findById(accountId).get();
        System.out.println("isFirstTx: " + isFirstTx + ", isSecondTx: " + isSecondTx + ", name: " + account.name);
        if (isFirstTx) {
            account.name = nameToUpdate;
            em.flush();
            Thread.sleep(3000);
        }

        if (isSecondTx) {
            Thread.sleep(1000);
            account.name = nameToUpdate;
        }
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void testLostUpdateOfREPEATABLE_READ(UUID accountId, boolean isFirstTx, boolean isSecondTx) throws InterruptedException {
        Account account = accountRepository.findById(accountId).get();
        System.out.println("isFirstTx: " + isFirstTx + ", isSecondTx: " + isSecondTx + ", name: " + account.name + ", address: " + account.address );
        if (isFirstTx) {
            account.name = "nameToUpdate";
            em.flush();
            Thread.sleep(3000);
        }

        if (isSecondTx) {
            Thread.sleep(1000);
            account.address = "addressToUpdate";
        }
    }

    @Transactional
    public void testLostUpdateOfREAD_COMMITTED(UUID accountId, boolean isFirstTx, boolean isSecondTx) throws InterruptedException {
        Account account = accountRepository.findById(accountId).get();
        System.out.println("isFirstTx: " + isFirstTx + ", isSecondTx: " + isSecondTx + ", name: " + account.name + ", address: " + account.address );
        if (isFirstTx) {
            account.name = "nameToUpdate";
            em.flush();
            Thread.sleep(3000);
        }

        if (isSecondTx) {
            Thread.sleep(1000);
            account.address = "addressToUpdate";
        }
    }
}
