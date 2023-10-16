package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    MyBeanA myBeanA() {
        return new MyBeanA("hello");
    }

    @Bean
    MyBeanB myBeanB() {
        return new MyBeanB("world");
    }
}

