package authenticationauthorization.usingjwtwithidpsinspring.infrastructure.cognito

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderAsyncClient
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient

@Configuration
class Config {

    @Bean
    fun cognitoIdentityProviderAsyncClient(): CognitoIdentityProviderAsyncClient {
        return CognitoIdentityProviderAsyncClient.create()
    }

    @Bean
    fun cognitoIdentityProviderClient(): CognitoIdentityProviderClient {
        return CognitoIdentityProviderClient.create()
    }
}