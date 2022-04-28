package spring.decorator01;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.decorator01.repo.*;
import spring.decorator01.svc.ConsistencyMemberService;
import spring.decorator01.svc.IdConversionMemberService;
import spring.decorator01.svc.MemberService;



/**
 * @author agj017@gmail.com
 * @since 2022/04/28
 */

@Configuration
public class MemberServiceConfiguration {

    @Autowired
    MemberService memberServiceImpl;

    @Autowired
    CertificationEmailRepository certificationEmailRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    
    MemberService idConversionMemberService(MemberService memberService){

        return new IdConversionMemberService(memberService);
    }


    MemberService consistencyMemberService(MemberService memberService){

        return new ConsistencyMemberService(memberService);
    }




}
