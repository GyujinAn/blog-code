package com.example.memberserver.common.validator;

import com.example.memberserver.member.MemberDto;

public class MemberValidator extends ValidatorTemplate<MemberDto> {
    @Override
    public void validateVariable(MemberDto dto) {

        validatePasswordFormat(dto.getPassword());

        validateEmailFormat(dto.getEmail());
    }

    @Override
    public void validateIntegrity(MemberDto dto) {

    }
}
