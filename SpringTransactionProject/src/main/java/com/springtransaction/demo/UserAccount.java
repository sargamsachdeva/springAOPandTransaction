package com.springtransaction.demo;

public class UserAccount {

    String name;
    Integer balance;

    public UserAccount(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
    }

    public UserAccount() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
