package spring.decorator01;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import spring.decorator01.svc.MemberService;

import javax.servlet.http.HttpServletResponse;

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


    @PostMapping
    public String singUp(@RequestBody Member member, HttpServletResponse httpServletResponse){

        try {
            memberService.save(member);
            return "회원가입 성공";
        } catch (Exception e) {
            e.printStackTrace();
            httpServletResponse.setStatus(400);
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

    @Autowired
    @Qualifier("memberServiceImpl")
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

}
