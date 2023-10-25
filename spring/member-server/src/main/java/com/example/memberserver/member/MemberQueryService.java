package com.example.memberserver.member;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MemberQueryService {
    private final MemberRepository memberRepository;

    public MemberQueryService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public MemberDto find(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        return MemberDto.of(member);
    }
}
