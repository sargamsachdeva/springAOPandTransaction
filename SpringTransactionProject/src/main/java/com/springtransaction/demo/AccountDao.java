package com.springtransaction.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

//CRUD operation Ques-2
@Service
public class AccountDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    public AccountDao(DataSource dataSource) {
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void create(String name, Integer balance){
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String sql = "insert into Account (name, balance) values (?, ?)";
            jdbcTemplateObject.update( sql, name, balance);

            System.out.println("Created Name = " + name + ", balance = " + balance);
            transactionManager.commit(status);
        }
        catch (DataAccessException e) {
            System.out.println("rolling back");
            transactionManager.rollback(status);
            throw e;
        }
        return;
    }

    public void update(String name , Integer balance) {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            String sql = "update Account set balance = ? where name = ?";
            jdbcTemplateObject.update( sql, new Object[]{balance,name});

            System.out.println("Updated Record with name = " + name );
            transactionManager.commit(status);
        }
        catch (DataAccessException e) {
            System.out.println("rolling back");
            transactionManager.rollback(status);
            throw e;
        }
        return;

    }

    public void delete(String name) {

            String sql = "delete from Account where name = ?";
            jdbcTemplateObject.update( sql, new Object[]{name});

            System.out.println("Updated Record with name = " + name );

    }

    @Transactional(readOnly = true) //ques-5
    public UserAccount getDetails(String name) {

        String sql = "select * from Account where name = ?";
       UserAccount u= jdbcTemplateObject.queryForObject(sql, new Object[]{name}, new RowMapper<UserAccount>() {
            public UserAccount mapRow(ResultSet resultSet,int i) throws SQLException{

                UserAccount userAccount = new UserAccount();
                userAccount.setBalance(resultSet.getInt("balance"));
                userAccount.setName(resultSet.getString("name"));
                return userAccount;
            }
        });
        System.out.println(u);
       return u;
    }
}
