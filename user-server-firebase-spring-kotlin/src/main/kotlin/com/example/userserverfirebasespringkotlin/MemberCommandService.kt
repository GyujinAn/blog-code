package com.example.userserverfirebasespringkotlin

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberCommandService(
    private val idpClient: IdpClient,
    private val memberRepository: MemberRepository,
    private val permissionRepository: PermissionRepository,
) {

    @Transactional(rollbackFor = [Exception::class])
    fun create(memberDto: MemberDto): Long? {
        if (memberDto.permissionIds.isNotEmpty() || memberDto.password.isEmpty() || memberDto.email.isEmpty()) {
            throw Exception()
        }
        val member = Member.of(memberDto)
        val savedMember = memberRepository.save(member)
        return idpClient.createMember(member = savedMember, password = memberDto.password)
    }

    @Transactional(rollbackFor = [Exception::class])
    fun update(memberId: Long, memberDto: MemberDto): Long? {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw Exception()
        val permissions = permissionRepository.findAllByIdIn(memberDto.permissionIds.map { it.toLong() })
        val updatedMember = member.assignPermissions(permissions)
        memberRepository.save(updatedMember)
        return idpClient.updateMember(updatedMember)
    }

    @Transactional(rollbackFor = [Exception::class])
    fun delete(memberId: Long) {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw Exception()
        memberRepository.delete(member)
        idpClient.deleteMember(member)
    }

}
