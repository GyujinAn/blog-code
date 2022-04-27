package designpatterns.decorator01;

/**
 * @author agj017@gmail.com
 * @since 2022/04/27
 */
public class StringLenthMemberService implements DecorateMemberService{

    MemberService memberService;

    private final int MEMBER_NAME_MAX_LENGTH = 10;

    public StringLenthMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public String save(Member member) {

        if(member.memberName.length() > MEMBER_NAME_MAX_LENGTH){
            return "Too long member name";
        }
        return memberService.save(member);

    }
}
