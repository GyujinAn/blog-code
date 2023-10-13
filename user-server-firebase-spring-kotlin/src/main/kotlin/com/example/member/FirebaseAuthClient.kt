package com.example.member

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import org.springframework.stereotype.Component

@Component
class FirebaseAuthClient: IdpClient {
    override fun createMember(member: Member, password: String): Long? {
        val createRequest = UserRecord.CreateRequest()
        createRequest.setUid(member.id.toString())
        createRequest.setEmail(member.email)
        createRequest.setPassword(password)
        val uid = FirebaseAuth.getInstance().createUser(createRequest).uid
        return uid.toLong()
    }

    override fun updateMember(member: Member): Long? {
        val claims = mapOf("permissions" to member.permissions.map { it.name })
        FirebaseAuth.getInstance().setCustomUserClaims(member.id.toString(), claims)
        return member.id
    }

    override fun deleteMember(member: Member) {
        FirebaseAuth.getInstance().deleteUser(member.id.toString())
    }
}
