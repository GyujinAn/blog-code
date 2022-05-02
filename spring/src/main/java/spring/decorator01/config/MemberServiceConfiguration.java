package spring.decorator01.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.decorator01.repo.*;
import spring.decorator01.svc.ConsistencyMemberService;
import spring.decorator01.svc.IdConversionMemberService;
import spring.decorator01.svc.MemberService;
import spring.decorator01.svc.MemberServiceImpl;


/**
 * @author agj017@gmail.com
 * @since 2022/04/28
 */

@Configuration
public class MemberServiceConfiguration {

    @Autowired
    CertificationEmailRepository certificationEmailRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MemberService memberServiceImpl;

    @Bean
    MemberService idConversionConsistencyMemberServiceImpl(){

        return new IdConversionMemberService(consistencyMemberServiceImpl());
    }

    @Bean
    MemberService consistencyMemberServiceImpl(){

        return new ConsistencyMemberService(memberServiceImpl, certificationEmailRepository, memberRepository, teamRepository);
    }


}
