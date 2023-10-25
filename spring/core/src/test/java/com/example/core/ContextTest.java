package com.example.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ContextTest {
    
    // https://docs.spring.io/spring-framework/reference/core/beans/basics.html#beans-factory-client
    // https://docs.spring.io/spring-framework/reference/core/beans/definition.html#beans-factory-class
    @Test
    public void using_the_container() throws BeansException {
        // given
        ApplicationContext context = new ClassPathXmlApplicationContext("configuration.xml");

        // when
        printBeanDefinitions(context);
        MyServiceA myServiceA = context.getBean("myServiceA", MyServiceA.class);
        MyServiceB myServiceB = context.getBean("myServiceB", MyServiceB.class);
        MyServiceC myServiceC = context.getBean("myServiceC", MyServiceC.class);

        // then
        assertEquals("a", myServiceA.getName());
        assertEquals("b", myServiceB.getName());
        assertEquals("c", myServiceC.getName());
    }

    // https://docs.spring.io/spring-framework/reference/core/beans/definition.html#beans-factory-type-determination
    @Test
    public void determining_a_beans_runtime_type() {
        // given
        ApplicationContext context = new ClassPathXmlApplicationContext("configuration.xml");

        // when
        Class<?> myServiceAType = context.getType("myServiceA");
        Class<?> myServiceBType = context.getType("myServiceB");
        Class<?> myServiceCType = context.getType("myServiceC");

        // then
        assert myServiceAType != null;
        assertEquals("com.example.core.MyServiceA", myServiceAType.getName());
        assert myServiceBType != null;
        assertEquals("com.example.core.MyServiceB", myServiceBType.getName());
        assert myServiceCType != null;
        assertEquals("com.example.core.MyServiceC", myServiceCType.getName());
    }

    private void printBeanDefinitions(ApplicationContext context) {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();

        System.out.println("Display bean definition names");
        for (String name : beanDefinitionNames) {
            System.out.println(name);
        }
    }
}

