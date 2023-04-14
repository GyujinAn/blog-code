package com.example.userserverfirebasespringkotlin


interface IdpClient {
    fun createMember(member: Member, password: String): Long?

    fun updateMember(member: Member): Long?

    fun deleteMember(member: Member)
}