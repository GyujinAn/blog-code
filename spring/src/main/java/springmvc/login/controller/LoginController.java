package springmvc.login.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springmvc.login.config.web.SessionUser;
import springmvc.login.service.LoginService;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class LoginController {

    LoginService loginService;

    @GetMapping("/login")
    public boolean login(@RequestParam String email,
                         @RequestParam String pw,
                         HttpSession httpSession){
        
        log.info("hello: "+email);
        log.info("world: "+pw);

        try {
            SessionUser sessionUser = loginService.login(email, pw);
            httpSession.setAttribute("user", sessionUser);
            return true;
        }catch (RuntimeException exception){
            log.error("login err: " + exception.getMessage());
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
