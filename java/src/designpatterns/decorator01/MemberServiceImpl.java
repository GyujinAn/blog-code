package designpatterns.decorator01;

import java.util.HashMap;
import java.util.Map;

/**
 * @author agj017@gmail.com
 * @since 2022/04/27
 */
public class MemberServiceImpl implements MemberService{

    Map<String, Member> memberStorage = new HashMap();

    @Override
    public String save(Member member) {
        memberStorage.put(member.getMemberName(), member);
        return "Save successfully";
    }

}
