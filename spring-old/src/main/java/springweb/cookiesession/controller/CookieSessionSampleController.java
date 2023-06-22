package springweb.cookiesession.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class CookieSessionSampleController {

    @GetMapping("/setSession")
    public void setSession(@RequestParam String key, @RequestParam String value,
                       HttpSession session){

        session.setAttribute(key, value);
        log.info("key: " + key + " value: " + value + " is set to session");
    }

    @GetMapping("/getSession")
    public String getSession(@RequestParam String key, HttpSession session){

        Object attribute = session.getAttribute(key);
        return attribute.toString();
    }

    @GetMapping("/getSessions")
    public Map getSessions(HttpSession session){

        Enumeration<String> attributeNames = session.getAttributeNames();
        HashMap<String, String> responseBody = new HashMap<>();

        while(attributeNames.hasMoreElements()){
            String key = attributeNames.nextElement();
            responseBody.put(key, (String) session.getAttribute(key));
        }

        return responseBody;
    }

    @GetMapping("/setCookie")
    public void setCookie(@RequestParam String key, @RequestParam String value,
                         HttpServletResponse httpServletResponse){

        httpServletResponse.addCookie(new Cookie(key, value));

    }


}
