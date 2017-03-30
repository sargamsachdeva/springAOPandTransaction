package com.springtransaction.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config.xml");
        AccountService accountService = applicationContext.getBean(AccountService.class);
        AccountDao accountDao = applicationContext.getBean(AccountDao.class);
    //  accountDao.create("ank",25000);
       // accountDao.create("sar",30000);
      //  accountDao.update("ank",100000);
       // accountDao.delete("sarg");
    //   accountDao.getDetails("ank");

        AccountTransfer accountTransfer = applicationContext.getBean(AccountTransfer.class);
    //    accountTransfer.transferAmount("ank","sar");
        //accountTransfer.transferAmount("ank","sar");
      //  accountTransfer.transferAmountUsingAnnotation("ank","sar");
        accountService.transferAmount("ank","sar",100);
    }
}
