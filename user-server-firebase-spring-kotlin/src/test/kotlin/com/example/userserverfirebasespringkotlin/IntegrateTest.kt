package com.example.userserverfirebasespringkotlin

import com.google.firebase.auth.FirebaseAuth
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.data.repository.findByIdOrNull
import java.util.UUID
import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrateTest {
    @Autowired
    private lateinit var permissionRepository: PermissionRepository

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var entityManager: EntityManager


    @Test
    fun `create member - assign permission - delete member`() {
        FirebaseAuth.getInstance().deleteUser("1")

        //given - create member
        val name = "test name"
        val email = "${UUID.randomUUID()}@domain.com"
        val password = "helloworld"
        val createMemberBody = MemberDto(name, email, password)

        //when - create member
        val createMemberResponse = restTemplate.postForEntity("/members", createMemberBody, String::class.java)

        val memberId = createMemberResponse.body
        //then - create member
        val createdMember = memberRepository.findByIdOrNull(memberId!!.toLong())
        assertThat(createdMember!!.name).isEqualTo(name)
        assertThat(createdMember.email).isEqualTo(email)
        val firebaseUser = FirebaseAuth.getInstance().getUser(memberId)
        assertThat(firebaseUser.email).isEqualTo(email)

        //given - assign permission
        val permission1 = permissionRepository.save(Permission(name = "resource:read"))
        val permission2 = permissionRepository.save(Permission(name = "resource:write"))
        val permissionIds = listOf(permission1.id.toString(), permission2.id.toString())
        val updateMemberBody = MemberDto(permissionIds = permissionIds)

        entityManager.flush()
        entityManager.clear()

        //when - assign permission
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val requestEntity = HttpEntity(updateMemberBody, headers)
        restTemplate.exchange("/members/{memberId}", HttpMethod.PUT, requestEntity, Long::class.java, memberId)
        val updatedMember = memberRepository.findByIdOrNull(memberId.toLong())
        val resultPermissionIds = updatedMember!!.permissions.map { it.id.toString() }
        assertThat(resultPermissionIds).isEqualTo(permissionIds)
        val updatedFirebaseUser = FirebaseAuth.getInstance().getUser(memberId)
        print("hello:" + updatedFirebaseUser.customClaims)


        FirebaseAuth.getInstance().deleteUser(memberId)


    }

}