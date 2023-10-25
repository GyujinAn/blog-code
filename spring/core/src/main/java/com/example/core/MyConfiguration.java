package com.example.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    MyServiceA myBeanA() {
        return new MyServiceA("hello");
    }

    @Bean
    MyServiceB myBeanB() {
        return new MyServiceB("world");
    }
}

