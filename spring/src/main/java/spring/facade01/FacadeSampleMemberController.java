package spring.facade01;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.facade01.domain.FacadeSampleMember;
import spring.facade01.svc.FacadeSampleMemberService;
import spring.facade01.svc.MemberFacade;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class FacadeSampleMemberController {

    private final MemberFacade memberFacade;

    @GetMapping
    public FacadeSampleMember getMember(@PathVariable Long id){

        memberFacade.findMember(id);
        memberFacade.findMemberWithTeam(id);
        memberFacade.findMemberWithItem(id);
        memberFacade.findMemberWithTeamAndItem(id);

        return null;
    }
}
