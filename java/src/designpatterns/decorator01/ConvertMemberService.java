package designpatterns.decorator01;

/**
 * @author agj017@gmail.com
 * @since 2022/04/27
 */
public class ConvertMemberService implements DecorateMemberService {

    MemberService memberService;

    public ConvertMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public String save(Member member) {
        if(member.gender.equals("남")){
            member.setGender("M");
        } else if(member.gender.equals("여")){
            member.setGender("W");
        } else {
            return "Invalid member gender";
        }
        return memberService.save(member);
    }

}
