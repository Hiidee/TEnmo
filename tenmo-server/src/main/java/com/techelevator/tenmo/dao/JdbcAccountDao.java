package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcAccountDao implements AccountDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT account_id, user_id, balance FROM account;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while (result.next()) {
            accounts.add(mapRowToAccount(result));
        }
        return accounts;
    }

    @Override
    public Account findByUserID(long user_id) {
        Account account = null;
        String sql = "SELECT account_id, user_id, balance FROM account " +
                "WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user_id);
        if (result.next()) {
            account = mapRowToAccount(result);
        }
        return account;
    }

    @Override
    public BigDecimal getBalance(long id) {
        String sql = "SELECT * FROM account WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            return mapRowToAccount(result).getBalance();
        }
        return null;
    }

    @Override
    public BigDecimal addToBalance(BigDecimal amountToAdd, long id) {
        Account account = findByUserID(id);
        BigDecimal newBalance = account.getBalance().add(amountToAdd);
        System.out.println(newBalance);
        String sql = "UPDATE account SET balance = ? WHERE user_id = ?;";
        try {
            jdbcTemplate.update(sql, newBalance, id);
        } catch (DataAccessException e) {
            System.out.println("There was an error accessing your data.");
        }
        return account.getBalance();
    }

    @Override
    public BigDecimal subtractFromBalance(BigDecimal amountToSubtract, long id) {
        Account account = findByUserID(id);
        BigDecimal newBalance = account.getBalance().subtract(amountToSubtract);
        System.out.println(newBalance);
        String sql = "UPDATE account SET balance = ? WHERE user_id = ?;";
        try {
            jdbcTemplate.update(sql, newBalance, id);
        } catch (DataAccessException e) {
            System.out.println("There was an error accessing your data.");
        }
        return account.getBalance();
    }

    private Account mapRowToAccount(SqlRowSet rs) {
        Account account = new Account();
        account.setAccountID(rs.getLong("account_id"));
        account.setUserID(rs.getLong("user_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        return account;
    }
}
