package com.example.memberserver.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {

    private String name;
    private String email;
    private String password;

    public static MemberDto of(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setName(member.getName());
        memberDto.setEmail(member.getEmail());
        return memberDto;
    }
}
