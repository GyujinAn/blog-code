package spring.decorator01.repo;


import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * @author agj017@gmail.com
 * @since 2022/04/28
 */
@Repository
public class CertificationEmailRepository {

    Set<String> certificatedEmails = new HashSet<>();

    @PostConstruct
    public void init(){
        certificatedEmails.add("agj017@gmail.com");
    }


    public boolean contains(String email){
        return certificatedEmails.contains(email);
    }
}
