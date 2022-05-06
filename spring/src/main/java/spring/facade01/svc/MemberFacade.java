package spring.facade01.svc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import spring.facade01.domain.FacadeSampleMember;
import spring.facade01.domain.FacadeSampleTeam;

@Component
@RequiredArgsConstructor
@Transactional
public class MemberFacade {

    private final FacadeSampleMemberService memberService;

    public FacadeSampleMember findMember(Long id){
        return memberService.findMember(id);
    }

    public FacadeSampleMember findMemberWithItem(Long id){
        FacadeSampleMember member = memberService.findMember(id);
        member.getItem();
        return member;
    }

    public FacadeSampleMember findMemberWithTeam(Long id){
        FacadeSampleMember member = memberService.findMember(id);
        member.getTeam();
        return member;
    }

    public FacadeSampleMember findMemberWithTeamAndItem(Long id){
        FacadeSampleMember member = memberService.findMember(id);
        member.getTeam();
        member.getItem();
        return member;
    }
}
