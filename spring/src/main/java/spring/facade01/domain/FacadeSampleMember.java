package spring.facade01.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class FacadeSampleMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private FacadeSampleTeam team;

    @ManyToOne(fetch = FetchType.LAZY)
    private FacadeSampleItem item;

}
