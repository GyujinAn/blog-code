package designpatterns.decorator01;

/**
 * @author agj017@gmail.com
 * @since 2022/04/27
 */
public class Main {

    public static void main(String[] args) {

        Member member = new Member();
        member.setMemberName("tom");
        member.setGender("남");

        MemberService memberService =
                new StringLenthMemberService(new ConvertMemberService(new MemberServiceImpl()));

        System.out.println(memberService.save(member));

    }
}
