package com.example.memberserver.member;

import com.example.memberserver.infrastructure.IdProvier.IdpClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberCommandService {
    private final IdpClient idpClient;
    private final MemberRepository memberRepository;

    @Transactional(rollbackFor = Exception.class)
    public Long create(MemberDto memberDto) {
        if (memberDto.getPassword().isEmpty() || memberDto.getEmail().isEmpty()) {
            throw new RuntimeException("Invalid member data");
        }
        Member member = Member.of(memberDto);
        Member savedMember = memberRepository.save(member);
        return idpClient.createMember(savedMember);
    }

    @Transactional(rollbackFor = Exception.class)
    public Long update(Long memberId, MemberDto memberDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        Member updatedMember = member.update(memberDto);
        memberRepository.save(updatedMember);
        return idpClient.updateMember(updatedMember);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("Member not found"));
        memberRepository.delete(member);
        idpClient.deleteMember(member);
    }
}
