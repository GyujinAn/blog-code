package com.example.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.TopicManagementResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class MessagingTest {

    // https://firebase.google.com/docs/cloud-messaging/manage-topics#java
    @Test
    public void create_topic() throws FirebaseMessagingException {
        String topicName = "topicName";

        // These registration tokens come from the client FCM SDKs.
        List<String> registrationTokens = Arrays.asList(
                "YOUR_REGISTRATION_TOKEN_1",
                // ...
                "YOUR_REGISTRATION_TOKEN_n"
        );

        // Subscribe the devices corresponding to the registration tokens to the
        // topic.
        TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(registrationTokens, topicName);
        // See the TopicManagementResponse reference documentation
        // for the contents of response.
        System.out.println(response.getSuccessCount() + " tokens were subscribed successfully");
    }

    // https://firebase.google.com/docs/cloud-messaging/send-message#send-messages-to-specific-devices
    @Test
    public void send_messages_to_specific_devices() throws FirebaseMessagingException {
        // This registration token comes from the client FCM SDKs.
        String registrationToken = "YOUR_REGISTRATION_TOKEN";

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

    // https://firebase.google.com/docs/cloud-messaging/send-message#send-messages-to-topics
    @Test
    public void send_messages_to_topics () throws FirebaseMessagingException {
        // The topic name can be optionally prefixed with "/topics/".
        String topic = createTopic();

        // See documentation on defining a message payload.
        Message message = Message.builder()
                .putData("title", "hello")
                .putData("contents", "world")
                .setTopic(topic)
                .build();

        // Send a message to the devices subscribed to the provided topic.
        String response = FirebaseMessaging.getInstance().send(message);
        // Response is a message ID string.
        System.out.println("Successfully sent message: " + response);

    }

    // https://firebase.google.com/docs/cloud-messaging/manage-topics#java
    private String createTopic() throws FirebaseMessagingException {
        String topicName = "topicName";

        // These registration tokens come from the client FCM SDKs.
        List<String> registrationTokens = Arrays.asList(
                "YOUR_REGISTRATION_TOKEN_1",
                // ...
                "YOUR_REGISTRATION_TOKEN_n"
        );

        // Subscribe the devices corresponding to the registration tokens to the
        // topic.
        TopicManagementResponse response = FirebaseMessaging.getInstance().subscribeToTopic(registrationTokens, topicName);
        // See the TopicManagementResponse reference documentation
        // for the contents of response.
        System.out.println(response.getSuccessCount() + " tokens were subscribed successfully");

        return topicName;
    }
}
