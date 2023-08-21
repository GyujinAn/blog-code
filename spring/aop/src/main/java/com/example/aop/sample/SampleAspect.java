package com.example.aop.sample;

import com.example.aop.audit.Account;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleAspect {

    @Before("com.example.aop.sample.SamplePointcuts.anyExecuteOfSample()")
    public void doSomethingBefore() {
        System.out.println("this is before advice");
    }

    @AfterReturning("com.example.aop.sample.SamplePointcuts.anyExecuteOfSample()")
    public void doSomethingAfterReturning() {
        System.out.println("this is after returning advice");
    }

    @AfterReturning(
            pointcut="com.example.aop.sample.SamplePointcuts.anyExecuteOfSample()",
            returning="account")
    public void doAccessCheck(Account account) {
        System.out.println("this is after returning advice with return obj " + account.id);
    }

    @AfterThrowing("com.example.aop.sample.SamplePointcuts.anyExecuteOfSample()")
    public void doSomethingAfterThrowing() {
        System.out.println("this is after throwing advice");
    }

    @AfterThrowing(
            pointcut="com.example.aop.sample.SamplePointcuts.anyExecuteOfSample()",
            throwing="ex")
    public void doSomethingAfterThrowing(Exception ex) {
        System.out.println("this is after throwing advice with exception");
    }

    @After("com.example.aop.sample.SamplePointcuts.anyExecuteOfSample()")
    public void doSomethingAfter() {
        System.out.println("this is after advice");
    }

    @Around("com.example.aop.sample.SamplePointcuts.anyExecuteOfSample()")
    public Object doSomethingAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("this is around before");
        Object retVal = pjp.proceed();
        System.out.println("this is around after");
        return retVal;
    }

    @Around(
            "com.example.aop.sample.SamplePointcuts.anyExecuteOfSample() && " + "args(sampleDto)"
    )
    public Object doSomethingAroundWithArg(
            ProceedingJoinPoint pjp,
            SampleDto sampleDto
    ) throws Throwable {
        System.out.println("this is around before");
        sampleDto.sample1 = "hello ";
        sampleDto.sample2 = "world ";
        sampleDto.sample3 = "!! ";
        SampleDto retVal = (SampleDto)pjp.proceed();
        System.out.println(retVal);
        System.out.println("this is around after");
        return retVal;
    }

    @Around("@annotation(com.example.aop.sample.SampleAnnotation)")
    public Object doSomethingAroundWithAnnotation(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("this is around before");
        Object retVal = pjp.proceed();
        System.out.println("this is around after");
        return retVal;
    }

}
