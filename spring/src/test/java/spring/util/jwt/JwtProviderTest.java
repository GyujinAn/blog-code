package spring.util.jwt;

import io.jsonwebtoken.Jwt;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JwtProviderTest {

    JwtProvider jwtProvider = new JwtProvider();

    @Test
    void createToken() {
        String jwtToken = jwtProvider.createToken("sampleId");

        System.out.println(jwtToken);
    }

    @Test
    void getUserId() {
        String jwtToken = jwtProvider.createToken("sampleId");

        String userId = jwtProvider.getUserId(jwtToken);

        System.out.println(userId);
    }

    @Test
    void getRoles() {
        String jwtToken = jwtProvider.createToken("sampleId");

        List<String> roles = jwtProvider.getRoles(jwtToken);

        for(String tmp : roles){
            System.out.println(tmp);
        }
    }

    @Test
    void validationToken() {
        String jwtToken = jwtProvider.createToken("sampleId");

        boolean b = jwtProvider.validationToken(jwtToken);

        System.out.println(b);
    }

}