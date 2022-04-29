package spring.decorator01.repo;


import org.springframework.stereotype.Repository;
import spring.decorator01.Member;

import java.util.HashMap;
import java.util.Map;

/**
 * @author agj017@gmail.com
 * @since 2022/04/28
 */
@Repository
public class MemberRepository {

    Map<String, Member> members = new HashMap();

    public Member save(Member member){

        members.put(member.getLoginId(), member);
        return members.get(member.getLoginId());

    }

    Member findByLoginId(String loginId){

        return members.get(loginId);

    }

    public boolean existsByLoginId(String loginId){
        return members.containsKey(loginId);
    }


}
