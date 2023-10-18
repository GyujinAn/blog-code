package com.example.firebase;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthenticationTest {

    // https://firebase.google.com/docs/auth/admin/manage-users#create_a_user
    @Test
    public void create_a_user() throws FirebaseAuthException {
        // given
        String email = "user@example.com";
        CreateRequest request = new CreateRequest()
                .setEmail(email);

        // when
        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

        // then
        assertEquals(email, userRecord.getEmail());
    }

    // https://firebase.google.com/docs/auth/admin/manage-users#update_a_user
    @Test
    public void update_a_user() throws FirebaseAuthException {
        // given
        String email = "user@example.com";
        String uid = FirebaseAuth
                .getInstance()
                .createUser(new CreateRequest().setEmail(email))
                .getUid();

        String updatedEmail = "update@example.com";
        UpdateRequest request = new UpdateRequest(uid)
                .setEmail(updatedEmail);

        // when
        UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
        
        // then
        assertEquals(updatedEmail, userRecord.getEmail());
    }

    @AfterEach
    public void cleanup() throws FirebaseAuthException {
        // Iterate through all users. This will still retrieve users in batches,
        // buffering no more than 1000 users in memory at a time.
        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        for (ExportedUserRecord user : page.iterateAll()) {
            FirebaseAuth.getInstance().deleteUserAsync(user.getUid());
            System.out.println("Successfully deleted user.");
        }
    }
}
