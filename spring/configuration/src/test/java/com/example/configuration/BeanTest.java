package com.example.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {
        "sample.a=goodbye",
        "sample.b=world"
})
public class BeanTest {

    @Autowired
    ConfigurableListableBeanFactory configurableListableBeanFactory;

    @Autowired
    ApplicationContext context;

    @Test
    public void display_bean_definition_name() {
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();

        System.out.println("Display bean definition names");
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    @Test
    public void custom_configuration_properties() {
        SampleConfigurationProperties sampleConfigurationProperties = (SampleConfigurationProperties) context.getBean("sample-com.example.configuration.SampleConfigurationProperties");

        assertEquals(sampleConfigurationProperties.getA() ,"goodbye");
        assertEquals(sampleConfigurationProperties.getB() ,"world");
    }
}
