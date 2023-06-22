package spring.facade01.svc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.facade01.FacadeSampleMemberRepository;
import spring.facade01.domain.FacadeSampleMember;

@Service
@RequiredArgsConstructor
public class FacadeSampleMemberServiceImpl implements FacadeSampleMemberService{

    private final FacadeSampleMemberRepository memberRepository;

    @Override
    public FacadeSampleMember findMember(Long id) {
        return memberRepository.findById(id).get();
    }

}
