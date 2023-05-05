package com.example.jpapostgrespringkotlin

import javax.persistence.EntityManager
import org.hibernate.Session
import org.hibernate.engine.jdbc.connections.internal.ConnectionProviderInitiator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class IsolationTestService (
    private val em: EntityManager,
) {
    private val logger = LoggerFactory.getLogger(IsolationTestService::class.java)


    @Transactional(isolation = Isolation.DEFAULT)
    fun withDefault() {
        printIsolationLevel()
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    fun withReadUncommitted() {
        printIsolationLevel()
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    fun withReadCommitted() {
        printIsolationLevel()
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun withRepeatableRead() {
        printIsolationLevel()
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    fun withSerializable() {
        printIsolationLevel()
    }

    private fun printIsolationLevel() {
        val session = em.delegate as Session
        session.doWork {
            val transactionIsolation = it.transactionIsolation
            val toIsolationNiceName = ConnectionProviderInitiator.toIsolationNiceName(transactionIsolation)
            logger.info("Transaction isolation level is $toIsolationNiceName")
        }
    }
}
