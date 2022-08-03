package springmvc.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.servlet.OAuth2ClientAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springmvc.login.domain.Role;
import springmvc.login.domain.User;
import springmvc.login.domain.UserRepository;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, OAuth2ClientAutoConfiguration.class})
public class LoginSampleApplication {

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(LoginSampleApplication.class);
    }


    @Bean
    public ApplicationRunner applicationRunner() {

        return args -> userRepository.save(User.builder()
                .email("sample@sample.com")
                .pw("sample")
                .role(Role.GUEST)
                .build());

    }

}
