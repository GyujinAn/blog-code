package spring.decorator01.svc;

import spring.decorator01.Member;

/**
 * @author agj017@gmail.com
 * @since 2022/04/28
 */
public interface MemberService {
    Member save(Member member) throws Exception;
}
