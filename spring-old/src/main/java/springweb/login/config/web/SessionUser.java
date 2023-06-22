package springweb.login.config.web;

import lombok.Builder;
import lombok.Getter;
import springweb.login.domain.Role;
import springweb.login.domain.User;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private Long id;

    private String email;

    private Role role;

    @Builder
    public SessionUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    @Override
    public String toString() {
        return "SessionUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
