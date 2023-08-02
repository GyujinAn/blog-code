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
        System.out.println("isFirstTx: " + isFirstTx + "isSecondTx: " + isSecondTx);
        if (isFirstTx) {
            Account account = accountRepository.findById(accountId).get();
            account.name = nameToUpdate;
            em.flush();
            Thread.sleep(3000);
        }

        if (isSecondTx) {
            Account account = accountRepository.findById(accountId).get();
            account.name = nameToUpdate;
        }
    }

    @Transactional
    public void testSecondTxWaiteLockFromFirstTx(UUID accountId, String nameToUpdate, boolean isFirstTx, boolean isSecondTx) throws InterruptedException {
        System.out.println("isFirstTx: " + isFirstTx + "isSecondTx: " + isSecondTx);
        if (isFirstTx) {
            Account account = accountRepository.findById(accountId).get();
            account.name = nameToUpdate;
            em.flush();
            Thread.sleep(3000);
        }

        if (isSecondTx) {
            Account account = accountRepository.findById(accountId).get();
            account.name = nameToUpdate;
        }
    }
}
