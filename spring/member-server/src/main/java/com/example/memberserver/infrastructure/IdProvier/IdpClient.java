package com.example.memberserver.infrastructure.IdProvier;

import com.example.memberserver.member.Member;

public interface IdpClient {
    Long createMember(Member member);

    Long updateMember(Member member);

    void deleteMember(Member member);
}
