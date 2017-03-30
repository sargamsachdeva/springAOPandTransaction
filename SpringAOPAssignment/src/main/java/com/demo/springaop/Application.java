package com.demo.springaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
        UserService userService = applicationContext.getBean(UserService.class);
        PersonService personService=applicationContext.getBean(PersonService.class);
        Class1 class1 = applicationContext.getBean(Class1.class);
        class1.method2();
       userService.addUser();
        userService.addPerson();
        try {
            userService.throwExceptionMethod2();

        }
        catch (Exception e)
        {}

     userService.depricatedMethod();

        personService.removePerson();
        System.out.println(personService.getPerson());
        personService.setPerson("SPRING");
    }
}
