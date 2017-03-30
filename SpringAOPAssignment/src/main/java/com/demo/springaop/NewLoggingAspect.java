package com.demo.springaop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component

//Ques-5
public class NewLoggingAspect {

    @Before("within(com.demo.springaop.PersonService.*)")
    public void logBeforeUsingWithin() {

        System.out.println("logging before within..");
    }

    @Before("args(String)")
    public void logBeforeUsingArgs() {

        System.out.println("logging before args..");
    }

    @Before("this(Class1)")
    public void logBeforeUsingthis() {

        System.out.println("logging before this..");
    }

}
