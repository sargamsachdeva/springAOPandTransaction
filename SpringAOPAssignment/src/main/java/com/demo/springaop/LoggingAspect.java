package com.demo.springaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* add*(..))")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println("Signature: "+joinPoint.getSignature());
        System.out.println("logging before..");
    }

    @After("execution(* add*(..)) && deprecatedAnnotatedMethod()")
    public void logAfter() {

        System.out.println("logging after..");
    }

    @AfterThrowing(value = "execution(* throw*(..))",throwing = "ex")
    public void logafterThrowingException(IOException ex) {

        System.out.println("logging after throwing exception:" + " " +ex);
    }

    @After("@annotation(Deprecated)")
    public void logAnnotatedMethod() {

        System.out.println("logging annotated method..");
    }

    @Pointcut("@annotation(Deprecated)")
    private void deprecatedAnnotatedMethod() {

        System.out.println("deprecated annotated method..");
    }


}
