package spring.decorator01.svc;

import lombok.RequiredArgsConstructor;
import spring.decorator01.Member;

/**
 * @author agj017@gmail.com
 * @since 2022/04/28
 */

@RequiredArgsConstructor
public class IdConversionMemberService implements DecorateMemberService{

    private final MemberService memberService;

    @Override
    public Member save(Member member) throws Exception {

        if( member.getEmail() == null || member.getEmail().equals("") )
            throw new Exception("email is not set");

        if( member.getLoginId() == null || member.getLoginId().equals("") )
            member.setLoginId(member.getEmail());

        return memberService.save(member);
    }
}
