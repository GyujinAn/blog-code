package com.example.jpapostgrespringkotlin

import javax.persistence.EntityManager
import org.assertj.core.api.AssertionsForClassTypes
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class EntityManagerTest {
    @Autowired
    private lateinit var jpaService: JpaService
    @Autowired
    private lateinit var jpaEntityRepository: JpaEntityRepository
    @Autowired
    private lateinit var entityManager: EntityManager

    @Transactional
    @Test
    fun entityManagerTest() {
        val jpaEntity = jpaService.saveJpaService()

        jpaEntityRepository.updateDiscription(id = jpaEntity.id, discription = "goodbye world")
        entityManager.clear()

        val actual = jpaEntityRepository.findByIdOrNull(jpaEntity.id)

        assertThat(actual!!.discription).isEqualTo("goodbye world")
    }


}
