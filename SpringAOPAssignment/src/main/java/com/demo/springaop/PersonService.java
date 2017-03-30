package com.demo.springaop;

import org.springframework.stereotype.Component;

@Component
public class PersonService {

    public void removePerson() {

        System.out.println("Removing user!!");
    }

    public void setPerson(String name) {

        System.out.println("Name is : " +name);
    }

    public String getPerson() {

        return "sargam";
    }



}
