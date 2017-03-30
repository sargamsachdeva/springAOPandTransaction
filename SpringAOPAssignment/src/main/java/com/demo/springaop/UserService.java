package com.demo.springaop;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class UserService {

    @Deprecated
    public void addUser() {

        System.out.println("Adding User!!");
    }

    public void addPerson() {

        System.out.println("Adding Person!!");
    }

    public void throwExceptionMethod1() throws SQLException {
        throw new SQLException();
    }

    public void throwExceptionMethod2() throws IOException {
        throw new IOException();

    }

    @Deprecated
    public void depricatedMethod() {

        System.out.println("deprecated method..");
    }
}
