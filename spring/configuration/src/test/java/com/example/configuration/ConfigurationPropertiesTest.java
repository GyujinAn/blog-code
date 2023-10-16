package com.example.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {
        "my.a=goodbye",
        "my.b=world"
})
public class ConfigurationPropertiesTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void custom_configuration_properties() {
        MyConfigurationProperties myConfigurationProperties = (MyConfigurationProperties) context.getBean("my-com.example.configuration.MyConfigurationProperties");

        assertEquals(myConfigurationProperties.getA() ,"goodbye");
        assertEquals(myConfigurationProperties.getB() ,"world");
    }
}
