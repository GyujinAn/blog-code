package spring.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class JwtProvider {
    private String secretKey = "helloworld";
    private List<String> roles = new ArrayList<>();
    private Long tokenValidMillisecond = 60 * 60 * 1000L;

    public JwtProvider() {
        init();
    }

    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        roles.add("admin");
        roles.add("user");
    }

    public String createToken(String userId){
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("roles", roles);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String getUserId(String token){

        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public List<String> getRoles(String token){

        return (List<String>)Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .get("roles");
    }

    public boolean validationToken(String token) {

        try{
            Jws<Claims> claimsJws = Jwts.parser().
                    setSigningKey(secretKey).
                    parseClaimsJws(token);

            return !claimsJws.getBody()
                    .getExpiration()
                    .before(new Date());

        }catch (Exception e){
            return false;
        }
    }


}
