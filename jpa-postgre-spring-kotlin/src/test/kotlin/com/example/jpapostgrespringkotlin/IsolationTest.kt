package com.example.jpapostgrespringkotlin

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.persistence.EntityManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class IsolationTest {

    @Autowired
    private lateinit var entityManager: EntityManager

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Test
    fun testIsolation() {
        // create two threads to concurrently update the same record
        val executor: ExecutorService = Executors.newFixedThreadPool(2)
        executor.submit {
            // load and update the record
            val account = entityManager.find(Account::class.java, 1L)
            account.balance = account.balance - 100
        }
        executor.submit {
            // load and update the same record
            val account = entityManager.find(Account::class.java, 1L)
            account.balance = account.balance + 100
        }
        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS)

        // verify that the final balance is unchanged
        val finalAccount = entityManager.find(Account::class.java, 1L)
        assertThat(finalAccount.balance).isEqualTo(1000L)
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    fun testReadUncommitted() {
        // create two threads to concurrently update the same record
        val executor = Executors.newFixedThreadPool(2)
        executor.submit {

            // load and update the record
            val account: Account = entityManager.find(Account::class.java, 1L)
            account.setBalance(account.getBalance() - 100)
        }
        executor.submit {

            // load and read the same record without waiting for the first thread to commit
            entityManager.transaction.setIsolationLevel(Connection.TRANSACTION_READ_UNCOMMITTED)
            val account: Account = entityManager.find(Account::class.java, 1L)
            assertThat(account.getBalance()).isEqualTo(1100L)
        }
        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS)

        // verify that the final balance is unchanged
        val finalAccount: Account = entityManager.find(Account::class.java, 1L)
        assertThat(finalAccount.getBalance()).isEqualTo(1000L)
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun testRepeatableRead() {
        // load the record outside of the transaction to establish a snapshot
        val account: Account = entityManager.find(Account::class.java, 1L)

        // create two threads to concurrently update the same record
        val executor = Executors.newFixedThreadPool(2)
        executor.submit {

            // load and update the record
            val accountToUpdate: Account = entityManager.find(Account::class.java, 1L)
            accountToUpdate.setBalance(accountToUpdate.getBalance() - 100)
        }
        executor.submit {

            // load and update the same record in a separate transaction
            entityManager.transaction.begin()
            val accountToUpdate: Account = entityManager.find(Account::class.java, 1L)
            accountToUpdate.setBalance(accountToUpdate.getBalance() + 100)
            entityManager.transaction.commit()
        }
        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS)

        // verify that the final balance is unchanged
        val finalAccount: Account = entityManager.find(Account::class.java, 1L)
        assertThat(finalAccount.getBalance()).isEqualTo(1000L)
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun testSerializable() {
        // create two threads to concurrently update the same record
        val executor = Executors.newFixedThreadPool(2)
        executor.submit {
            // load and update the record
            val account = entityManager.find(Account::class.java, 1L)
            account.balance -= 100
        }
        executor.submit {
            // load and update the same record
            val account = entityManager.find(Account::class.java, 1L)
            account.balance += 100
        }
        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS)

        // verify that the final balance is unchanged
        val finalAccount = entityManager.find(Account::class.java, 1L)
        assertThat(finalAccount.balance).isEqualTo(1000L)
    }
}
