package com.example.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BeanTest {

    @Autowired
    ConfigurableListableBeanFactory configurableListableBeanFactory;

    @Test
    public void display_bean_definition_names() {
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();

        System.out.println("Display bean definition names");
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }
}
