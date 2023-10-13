package com.example.memberserver.infrastructure.aws

import com.example.memberserver.domain.member.IdpClient
import com.example.memberserver.domain.member.Member

class CognitoClient: IdpClient {
    override fun createMember(member: Member, password: String): Long? {
        TODO("Not yet implemented")
    }

    override fun updateMember(member: Member): Long? {
        TODO("Not yet implemented")
    }

    override fun deleteMember(member: Member) {
        TODO("Not yet implemented")
    }
}
