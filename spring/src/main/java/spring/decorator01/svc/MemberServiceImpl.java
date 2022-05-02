package spring.decorator01.svc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import spring.decorator01.Member;
import spring.decorator01.repo.MemberRepository;

/**
 * @author agj017@gmail.com
 * @since 2022/04/28
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member save(Member member) throws Exception {
        log.info("stared MemberServiceImpl");

        log.info("ended MemberServiceImpl");
        return memberRepository.save(member);
    }
}
