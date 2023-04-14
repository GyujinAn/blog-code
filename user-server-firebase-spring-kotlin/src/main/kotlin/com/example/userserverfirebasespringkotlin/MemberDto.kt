package com.example.userserverfirebasespringkotlin

data class MemberDto(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val permissionIds: List<String> = listOf(),
    val permissionNames: List<String> = listOf(),
) {

    companion object {
        fun of(member: Member): MemberDto {
            return MemberDto(
                name = member.name,
                email = member.email,
                permissionNames = member.permissions.map { it.name }
            )
        }
    }
}
