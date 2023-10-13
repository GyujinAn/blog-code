package com.example.memberserver.domain.member

import com.example.memberserver.domain.member.Member


interface IdpClient {
    fun createMember(member: Member, password: String): Long?

    fun updateMember(member: Member): Long?

    fun deleteMember(member: Member)
}
