package com.example.memberserver.member;

import com.example.memberserver.common.BaseEntity;
import com.example.memberserver.permission.Permission;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.Instant;
import java.util.List;

@Getter
@Entity
public class Member extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToMany
    private List<Permission> permissions;

    public Member() {
        super();
    }

    private Member(Long id, String name, String email, List<Permission> permissions, Instant createdAt, Instant updatedAt) {
        super(createdAt, updatedAt);
        this.id = id;
        this.name = name;
        this.email = email;
        this.permissions = permissions;
    }

    public Member(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    private Member copy() {
        return new Member(id, name, email, permissions, createdAt, updatedAt);
    }

    public static Member of(MemberDto memberDto) {
        return new Member(memberDto.getName(), memberDto.getEmail(), memberDto.getPassword());
    }

    public Member update(MemberDto memberDto) {
        return null;
    }
}
