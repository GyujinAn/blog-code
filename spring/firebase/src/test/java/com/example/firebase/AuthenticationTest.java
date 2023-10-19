package com.example.firebase;

import com.google.firebase.auth.ExportedUserRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.ListUsersPage;
import com.google.firebase.auth.UserMetadata;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import com.google.firebase.auth.UserRecord.UpdateRequest;
import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AuthenticationTest {

    // https://firebase.google.com/docs/auth/admin/manage-users#retrieve_user_data
    @Test
    public void retrieve_user_data() throws FirebaseAuthException {
        // given
        long nowSeconds = System.currentTimeMillis();
        String uid = createUser().getUid();

        // when
        UserRecord userRecord = FirebaseAuth.getInstance().getUser(uid);

        // then
        UserMetadata userMetadata = userRecord.getUserMetadata();
        assertTrue(nowSeconds < userMetadata.getCreationTimestamp());
    }

    // https://firebase.google.com/docs/auth/admin/manage-users#create_a_user
    @Test
    public void create_a_user() throws FirebaseAuthException {
        // given
        String email = UUID.randomUUID() + "@example.com";
        String password = "password";
        CreateRequest request = new CreateRequest()
                .setEmail(email)
                .setPassword(password)
                ;

        // when
        UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

        // then
        assertEquals(email, userRecord.getEmail());
    }

    // https://firebase.google.com/docs/auth/admin/manage-users#update_a_user
    @Test
    public void update_a_user() throws FirebaseAuthException {
        // given
        String uid = createUser().getUid();
        String updatedEmail = UUID.randomUUID() + "@example.com";
        UpdateRequest request = new UpdateRequest(uid)
                .setEmail(updatedEmail);

        // when
        UserRecord userRecord = FirebaseAuth.getInstance().updateUser(request);
        
        // then
        assertEquals(updatedEmail, userRecord.getEmail());
    }

    @Test
    public void delete_all_user() throws FirebaseAuthException {
        // given
        createUser();
        createUser();
        createUser();

        // Iterate through all users. This will still retrieve users in batches,
        // buffering no more than 1000 users in memory at a time.
        ListUsersPage page = FirebaseAuth.getInstance().listUsers(null);
        for (ExportedUserRecord user : page.iterateAll()) {
            // when
            FirebaseAuth.getInstance().deleteUserAsync(user.getUid());
        }

        // then
        ListUsersPage result = FirebaseAuth.getInstance().listUsers(null);
        assertFalse(result.hasNextPage());

    }

    // https://firebase.google.com/docs/auth/admin/manage-sessions#revoke_refresh_tokens
    @Test
    public void revoke_refresh_tokens() throws FirebaseAuthException {
        // given
        long nowSeconds = System.currentTimeMillis() / 1000;
        String uid = createUser().getUid();

        // when
        FirebaseAuth.getInstance().revokeRefreshTokens(uid);
        UserRecord user = FirebaseAuth.getInstance().getUser(uid);
        // Convert to seconds as the auth_time in the token claims is in seconds too.
        long revocationSecond = user.getTokensValidAfterTimestamp() / 1000;

        // then
        assertTrue(nowSeconds < revocationSecond);
    }

    private UserRecord createUser() throws FirebaseAuthException {
        String email = UUID.randomUUID() + "@example.com";
        CreateRequest request = new CreateRequest()
                .setEmail(email);

        return FirebaseAuth.getInstance().createUser(request);
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
