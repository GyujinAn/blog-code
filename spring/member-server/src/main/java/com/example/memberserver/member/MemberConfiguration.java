package com.example.memberserver.member;

import com.example.memberserver.common.validator.MemberValidator;
import com.example.memberserver.common.validator.ValidatorTemplate;
import com.example.memberserver.infrastructure.IdProvier.IdpClient;
import com.example.memberserver.infrastructure.IdProvier.aws.CognitoClient;
import com.example.memberserver.infrastructure.IdProvier.firebase.FirebaseAuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberConfiguration {

    @Value("${member.idp}")
    private IdpProvider idp;

    @Bean
    public IdpClient idpClient() {
        if (idp == IdpProvider.FIREBASE) {
            return new FirebaseAuthClient();
        }

        if (idp == IdpProvider.AWS) {
            return new CognitoClient();
        }

        throw new RuntimeException("Unsupported IdpProvider");
    }

    @Bean
    public ValidatorTemplate<MemberDto> memberValidator() {
        return new MemberValidator();
    }
}

enum IdpProvider {
    FIREBASE, AWS
}
