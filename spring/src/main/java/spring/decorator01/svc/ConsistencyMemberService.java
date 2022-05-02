package spring.decorator01.svc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.decorator01.Member;
import spring.decorator01.repo.CertificationEmailRepository;
import spring.decorator01.repo.MemberRepository;
import spring.decorator01.repo.TeamRepository;

/**
 * @author agj017@gmail.com
 * @since 2022/04/28
 */
@Slf4j
@RequiredArgsConstructor
public class ConsistencyMemberService implements DecorateMemberService{

    private final MemberService memberService;

    private final CertificationEmailRepository certificationEmailRepository;

    private final MemberRepository memberRepository;

    private final TeamRepository teamRepository;

    @Override
    public Member save(Member member) throws Exception {
        log.info("stared ConsistencyMemberService decorator");
        if(memberRepository.existsByLoginId(member.getLoginId()))
            throw new Exception("the loginId is already existed");


        if(!certificationEmailRepository.contains(member.getEmail()))
            throw new Exception("the email is not certificated");


        if(!teamRepository.contains(member.getTeam()))
            throw new Exception("the team is not registered");


        log.info("ended ConsistencyMemberService decorator");
        return memberService.save(member);

    }

}
