package com.example.userserverfirebasespringkotlin

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource;
import javax.annotation.PostConstruct


@Configuration
class FirebaseAuthConfig(
    @Value("classpath:firebase-service-account.json")
    var serviceAccount: Resource
) {
    @PostConstruct
    fun firebaseAuth() {
        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount.inputStream))
            .setConnectTimeout(5000)
            .setReadTimeout(5000)
            .build()
        if(FirebaseApp.getApps().isEmpty()) { FirebaseApp.initializeApp(options) }
    }
}
