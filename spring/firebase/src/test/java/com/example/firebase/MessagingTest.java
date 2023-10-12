package com.example.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessagingTest {

    // https://firebase.google.com/docs/cloud-messaging/send-message#send-messages-to-specific-devices
    @Test
    public void send_messages_to_specific_devices() throws FirebaseMessagingException {
        // This registration token comes from the client FCM SDKs.
        String registrationToken = "c6KoyexKDV1p50w6vaMXta:APA91bEOLppZ3Zwi-gXBRcdT-NXWccKMdRzLApoBkpAfQ6rmgtuo_aTamqXQK8iF42zPpY7Pp5Ze_quVqy0seZ_pY8TFeakil6vgfqE0vbrakD9DoZvBFjCYLkWBjY6JPlTlmGw9Z-kh";

        // See documentation on defining a message payload.
        Message message = Message.builder()
                .putData("title", "hello")
                .putData("contents", "world")
                .setToken(registrationToken)
                .build();

        // Send a message to the device corresponding to the provided
        // registration token.
        String response = FirebaseMessaging.getInstance().send(message);
        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);
    }

}
