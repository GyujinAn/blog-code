package springweb.login.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String pw;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String email, String pw, Role role) {
        this.email = email;
        this.pw = pw;
        this.role = role;
    }

    public User checkPw(String pw){

        if(!this.pw.equals(pw))
            throw new IllegalArgumentException("비밀번호 불일치");

        return this;
    }


}
