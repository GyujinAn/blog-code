package spring.cookiesession.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class CookieSessionSampleController {

    @GetMapping("/hellow")
    public void hellow(@RequestParam String key,
                       @RequestParam String value,
                       HttpSession session){

        session.setAttribute(key, value);
        System.out.println(session.toString());

    }
}
