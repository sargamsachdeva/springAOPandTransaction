package com.springtransaction.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;


@Service
public class AccountService {

    @Autowired
    AmountTransferQues6 amountTransferQues6;

    JdbcTemplate jdbcTemplate;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    public AccountService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }


    @Transactional
    public void transferAmount(String sname , String rname,Integer balance) {

            String sql = "update Account set balance = balance-? where name = ?";
            jdbcTemplate.update(sql, new Object[]{balance, sname});

            System.out.println("Updated Record with sname = " + sname);

            String sql1 = "update Account set balance = balance+?  where name = ?";
            jdbcTemplate.update(sql1, new Object[]{balance, rname});

            System.out.println("Updated Record with rname = " + rname);

            try {
                amountTransferQues6.save(sname, rname, balance);
            }

            catch (ArithmeticException e){}
        }
    }


