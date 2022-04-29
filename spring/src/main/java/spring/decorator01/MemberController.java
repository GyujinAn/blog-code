package spring.decorator01;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.decorator01.svc.MemberService;

/**
 * @author agj017@gmail.com
 * @since 2022/04/28
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    private final MemberService idConversionConsistencyMemberServiceImpl;

    private final MemberService consistencyMemberServiceImpl;

    private final MemberService idConversionMemberServiceImpl;

    @PostMapping
    public String singUp(Member member){

        try {
            memberService.save(member);
            return "회원가입 성공";
        } catch (Exception e) {
            e.printStackTrace();
            return "회원가입 실패: " + e.getMessage();
        }
    }

    @PutMapping("/config/idconversion-consistency")
    public void setIdConversionConsistency(){
        this.setMemberService(idConversionConsistencyMemberServiceImpl);
    }

    @PutMapping("/config/consistency")
    public void setConsistency(){
        this.setMemberService(consistencyMemberServiceImpl);
    }

    @PutMapping("/config/idconversion")
    public void setIdConversion(){
        this.setMemberService(idConversionMemberServiceImpl);
    }

    @Qualifier("memberServiceImpl")
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }


}
