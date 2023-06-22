package springsecurity.oauth2client.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springsecurity.oauth2client.config.dto.SessionUser;

import javax.servlet.http.HttpSession;


@RestController
public class Oauth2ClientSampleController {

    @GetMapping("/test/guest")
    public String guest(HttpSession httpSession){
        SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");


        return sessionUser.toString();
    }

    @GetMapping("/test/user")
    public String user(HttpSession httpSession){
        SessionUser sessionUser = (SessionUser)httpSession.getAttribute("user");


        return sessionUser.toString();
    }
}
