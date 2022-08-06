package springweb.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import springweb.login.config.web.SessionUser;
import springweb.login.domain.User;
import springweb.login.domain.UserRepository;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;

    public SessionUser login(String email, String pw){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자"))
                .checkPw(pw);

        return new SessionUser(user);
    }
}
