package com.springtransaction.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

@Service
public class AccountTransfer {

    JdbcTemplate jdbcTemplate;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Autowired
    public AccountTransfer(DataSource dataSource)
    {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PlatformTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    //using programmatic transaction
    public void transferAmount(String sname , String rname) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String sql = "update Account set balance = balance-100  where name = ?";
            jdbcTemplate.update( sql, new Object[]{sname});

            System.out.println("Updated Record with name = " + sname );

            String sql1 = "update Account set balance = balance+100  where name = ?";
            jdbcTemplate.update( sql1, new Object[]{rname});

            System.out.println("Updated Record with name = " + rname );
            transactionManager.commit(status);
        }
        catch (DataAccessException e) {
            System.out.println("rolling back");
            transactionManager.rollback(status);
            throw e;
        }
        return;

    }

    //Using declarative transaction
    @Transactional()
    public void transferAmountUsingAnnotation(String sname , String rname) {

            String sql = "update Account set balance = balance-100  where name = ?";
            jdbcTemplate.update( sql, new Object[]{sname});

            System.out.println("Updated Record with name = " + sname );

            String sql1 = "update Account set balance = balance+100  where name = ?";
            jdbcTemplate.update( sql1, new Object[]{rname});

            System.out.println("Updated Record with name = " + rname );

    }


}
