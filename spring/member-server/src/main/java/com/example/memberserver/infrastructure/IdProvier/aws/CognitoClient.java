package com.example.memberserver.infrastructure.IdProvier.aws;

import com.example.memberserver.infrastructure.IdProvier.IdpClient;
import com.example.memberserver.member.Member;

public class CognitoClient implements IdpClient {
    @Override
    public Long createMember(Member member) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Long updateMember(Member member) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void deleteMember(Member member) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
