package com.example.memberserver.domain.member

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberQueryService(
    private val memberRepository: MemberRepository
) {
    fun find(memberId: Long?): MemberDto {
        val member = memberRepository.findByIdOrNull(memberId) ?: throw Exception()

        return MemberDto.of(member)
    }

}
