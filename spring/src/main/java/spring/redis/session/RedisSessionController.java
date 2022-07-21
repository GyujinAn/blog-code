package spring.redis.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
public class RedisSessionController {

    @GetMapping("/setSession")
    public void setSession(@RequestParam String key, @RequestParam String value,
                           HttpSession session){

        session.setAttribute(key, value);
        log.info("key: " + key + " value: " + value + " is set to session");
    }
}
