package springmvc.login.config.web;

import lombok.Builder;
import lombok.Getter;
import springmvc.login.domain.Role;
import springmvc.login.domain.User;

@Getter
public class SessionUser {

    private Long id;

    private String email;

    private Role role;

    @Builder
    public SessionUser(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.role = user.getRole();
    }
}
