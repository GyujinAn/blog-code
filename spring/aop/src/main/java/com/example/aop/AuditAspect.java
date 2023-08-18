package com.example.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    @Before("com.example.aop.Pointcuts.anyExecute()")
    public void doAccessCheck() {
        System.out.println("hello world");
    }


}
