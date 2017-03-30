package com.springtransaction.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Service
public class AmountTransferQues6 {

     JdbcTemplate jdbcTemplate;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    public AmountTransferQues6(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(String sender , String receiver,Integer balance_transferred) {

            int a = 1 / 0;

            String sql = "insert into account_transaction (sender,receiver,balance_transferred) values (?,?,?)";
            jdbcTemplate.update( sql,sender, receiver,balance_transferred);

            System.out.println("Created sender = " + sender + ", receiver = " + receiver + ", balance_transferrred = " + balance_transferred);

    }
    }
