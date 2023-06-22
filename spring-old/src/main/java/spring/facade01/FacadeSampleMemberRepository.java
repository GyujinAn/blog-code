package spring.facade01;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.facade01.domain.FacadeSampleMember;

public interface FacadeSampleMemberRepository extends JpaRepository<FacadeSampleMember, Long> {
}
