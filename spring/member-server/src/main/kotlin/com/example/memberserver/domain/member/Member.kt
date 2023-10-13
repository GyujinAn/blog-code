package com.example.memberserver.domain.member

import com.example.memberserver.domain.permission.Permission
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EntityListeners
import javax.persistence.Id
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.ManyToMany

@Entity
@EntityListeners(AuditingEntityListener::class)
class Member(
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    var id: Long? = null,

    @Column
    val name: String = "",

    @Column
    val email: String,

    @ManyToMany
    var permissions: List<Permission> = listOf(),

    @CreatedDate
    @Column(updatable = false)
    val createdAt: Instant = Instant.EPOCH,

    @LastModifiedDate
    @Column
    val updatedAt: Instant = Instant.EPOCH,

    ) {

    fun assignPermissions(permissions: List<Permission>): Member {
        this.permissions = permissions
        return copy()
    }

    private fun copy(): Member {
        return Member(
            id = id,
            name = name,
            email = email,
            permissions = permissions,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    companion object {
        fun of(memberDto: MemberDto): Member {
            return Member(
                name = memberDto.name,
                email = memberDto.email,
            )
        }

    }
}
