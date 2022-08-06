package springweb.login.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springweb.login.config.web.SessionUser;
import springweb.login.service.LoginService;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@RestController
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public boolean login(@RequestParam String email,
                         @RequestParam String pw,
                         HttpSession httpSession){

        try {
            SessionUser sessionUser = loginService.login(email, pw);
            httpSession.setAttribute("user", sessionUser);
            return true;
        }catch (RuntimeException exception){
            log.error("login err: " + exception.getMessage());
            exception.printStackTrace();

            return false;
        }

    }

    @GetMapping("/test/guest")
    public String guest(){

        return "hello guest";
    }

    @GetMapping("/test/user")
    public String user(){

        return "hello user";
    }
}
