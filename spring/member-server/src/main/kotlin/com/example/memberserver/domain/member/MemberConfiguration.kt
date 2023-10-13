package com.example.memberserver.domain.member

import com.example.memberserver.domain.member.IdpProvider.AWS
import com.example.memberserver.domain.member.IdpProvider.FIREBASE
import com.example.memberserver.infrastructure.aws.CognitoClient
import com.example.memberserver.infrastructure.firebase.FirebaseAuthClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MemberConfiguration(
    @Value("\${member.idp}")
    private val idp: IdpProvider,
) {

    @Bean
    fun ipdClient(): IdpClient {
        if (idp == FIREBASE) {
            return FirebaseAuthClient()
        }

        if (idp == AWS) {
            return CognitoClient()
        }

        throw Exception()
    }
}

enum class IdpProvider {
    FIREBASE, AWS
}
