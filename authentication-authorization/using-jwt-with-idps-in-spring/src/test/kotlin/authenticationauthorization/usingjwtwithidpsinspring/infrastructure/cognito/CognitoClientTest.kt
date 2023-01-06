package authenticationauthorization.usingjwtwithidpsinspring.infrastructure.cognito

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminAddUserToGroupRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminDeleteUserRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminGetUserRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType
import software.amazon.awssdk.services.cognitoidentityprovider.model.CreateGroupRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.DeleteGroupRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.GetGroupRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.ListUsersInGroupRequest
import software.amazon.awssdk.services.cognitoidentityprovider.model.MessageActionType
import software.amazon.awssdk.services.cognitoidentityprovider.model.ResourceNotFoundException
import software.amazon.awssdk.services.cognitoidentityprovider.model.UserNotFoundException

@SpringBootTest
class CognitoClientTest {
    private val cognitoClient: CognitoIdentityProviderClient = CognitoIdentityProviderClient.create()
    @Value("\${user.userPoolId}") private lateinit var userPoolId: String
    val email = "test@onionev.com"
    val group = "testGroup"
    val phoneNumber = "+855123456780"

    @BeforeEach
    fun init(){
        createCognitoUser(email, phoneNumber)
        createCognitoGroup(group)
    }

    @AfterEach
    fun destroy() {
        deleteCognitoGroup(group)
        deleteCognitoUser(email)
    }


    private fun createCognitoUser(email: String, phoneNumber: String) {
        val adminGetUserRequest = AdminGetUserRequest.builder()
            .userPoolId(userPoolId)
            .username(email)
            .build()

        try {
            cognitoClient.adminGetUser(adminGetUserRequest)
        } catch (e:UserNotFoundException){
            val userAttributes = mutableListOf<AttributeType>()
            userAttributes.add(AttributeType.builder().name("email").value(email).build())
            userAttributes.add(AttributeType.builder().name("phone_number").value(phoneNumber).build())
            userAttributes.add(AttributeType.builder().name("phone_number_verified").value("true").build())
            val createUserRequest = AdminCreateUserRequest.builder()
                .userPoolId(userPoolId)
                .username(email)
                .userAttributes(userAttributes)
                .messageAction(MessageActionType.SUPPRESS)
                .build()

            cognitoClient.adminCreateUser(createUserRequest)
        }
    }

    private fun createCognitoGroup(groupName: String) {
        val getGroupRequest = GetGroupRequest.builder()
            .userPoolId(userPoolId)
            .groupName(groupName)
            .build()
        try {
            cognitoClient.getGroup(getGroupRequest)
        }catch (e:ResourceNotFoundException) {
            val createGroupRequest = CreateGroupRequest.builder()
                .groupName(groupName)
                .userPoolId(userPoolId)
                .build()
            cognitoClient.createGroup(createGroupRequest)
        }

    }

    private fun deleteCognitoGroup(groupName: String) {
        val getGroupRequest = GetGroupRequest.builder()
            .groupName(groupName)
            .userPoolId(userPoolId)
            .build()

        val deleteGroupRequest = DeleteGroupRequest.builder()
            .groupName(groupName)
            .userPoolId(userPoolId)
            .build()

        try{
            val group = cognitoClient.getGroup(getGroupRequest)
            if(group.group().groupName() == groupName) {
                cognitoClient.deleteGroup(deleteGroupRequest)
            }
        } catch (e: ResourceNotFoundException) {
            return
        }
    }

    private fun deleteCognitoUser(userName: String) {
        val adminGetUserRequest = AdminGetUserRequest.builder()
            .userPoolId(userPoolId)
            .username(userName)
            .build()

        val adminDeleteUserRequest = AdminDeleteUserRequest.builder()
            .userPoolId(userPoolId)
            .username(userName)
            .build()

        try {
            val adminGetUser = cognitoClient.adminGetUser(adminGetUserRequest)
            if(adminGetUser.username() == userName) {
                cognitoClient.adminDeleteUser(adminDeleteUserRequest)
            }
        } catch (e:UserNotFoundException){
            return
        }
    }

    @Test
    fun `admin add user to group`(){
        val adminAddUserToGroupRequest = AdminAddUserToGroupRequest.builder()
            .groupName(group)
            .userPoolId(userPoolId)
            .username(email)
            .build()

        val adminGetUserRequest = AdminGetUserRequest.builder()
            .userPoolId(userPoolId)
            .username(email)
            .build()

        val listUsersInGroupRequest = ListUsersInGroupRequest.builder()
            .groupName(group)
            .userPoolId(userPoolId)
            .build()

        cognitoClient.adminAddUserToGroup(adminAddUserToGroupRequest)

        val actualUserName = cognitoClient.adminGetUser(adminGetUserRequest).username()
        val usernames = cognitoClient.listUsersInGroup(listUsersInGroupRequest).users()
            .map { it.username() }
        assertTrue(usernames.contains(actualUserName))
    }

}
