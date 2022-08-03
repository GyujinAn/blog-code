package springmvc.login.service;

import org.springframework.stereotype.Service;
import springmvc.login.config.web.SessionUser;
import springmvc.login.controller.LoginRequestDto;
import springmvc.login.domain.User;
import springmvc.login.domain.UserRepository;

@Service
public class LoginService {

    UserRepository userRepository;

    public SessionUser login(String email, String pw){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자"))
                .checkPw(pw);

        return new SessionUser(user);
    }
}
