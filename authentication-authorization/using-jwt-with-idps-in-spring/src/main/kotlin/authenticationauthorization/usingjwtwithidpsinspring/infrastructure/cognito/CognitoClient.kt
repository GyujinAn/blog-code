package authenticationauthorization.usingjwtwithidpsinspring.infrastructure.cognito

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserResponse
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType
import software.amazon.awssdk.services.cognitoidentityprovider.model.MessageActionType

@Component
class CognitoClient(
    private val cognitoIdPClient: CognitoIdentityProviderClient,
    @Value("\${user.userPoolId}")
    private val userPoolId: String

) {

    fun adminCreateUser(name: String, email: String? = null, phoneNumber: String? = null): AdminCreateUserResponse {
        if (email.isNullOrBlank() && phoneNumber.isNullOrBlank()) {
            throw IllegalArgumentException("Either email or phone number must not be null")
        }

        val userAttributes = mutableListOf<AttributeType>()
        userAttributes.add(AttributeType.builder().name("name").value(name).build())

        if (phoneNumber?.isNotEmpty() == true){
            userAttributes.add(AttributeType.builder().name("phone_number").value(phoneNumber).build())
            userAttributes.add(AttributeType.builder().name("phone_number_verified").value("true").build())
        }

        if (email?.isNotEmpty() == true) {
            userAttributes.add(AttributeType.builder().name("email").value(email).build())
        }

        val createUserRequest = AdminCreateUserRequest.builder()
            .userPoolId(userPoolId)
            .username(email ?: phoneNumber)
            .userAttributes(userAttributes)
            .messageAction(MessageActionType.SUPPRESS)
            .build()

        return cognitoIdPClient.adminCreateUser(createUserRequest)
    }
}