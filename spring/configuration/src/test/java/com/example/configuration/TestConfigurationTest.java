package com.example.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestConfigurationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    ConfigurableListableBeanFactory configurableListableBeanFactory;

    @Test
    public void get_bean_from_inner_test_configuration() {
        displayBeanDefinitionNames();

        MyBeanA myBeanA = (MyBeanA) context.getBean("myBeanA");

        assertEquals(myBeanA.getName(), "goodbye");
    }

    @Test
    public void get_bean_from_top_level_test_configuration() {
        displayBeanDefinitionNames();

        MyBeanB myBeanB = (MyBeanB) context.getBean("myBeanB");

        assertEquals(myBeanB.getName(), "world");
    }

    private void displayBeanDefinitionNames() {
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();

        System.out.println("Display bean definition names");
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        MyBeanA myBeanA() {
            return new MyBeanA("goodbye");
        }
    }
}

@TestConfiguration
class TestConfig {
    @Bean
    MyBeanB myBeanB() {
        return new MyBeanB("earth");
    }
}
