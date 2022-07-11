package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean createTransfer(Transfer transfer) {
        String sql = "INSERT INTO transfer (transfer_id, transfer_type_id, transfer_status_id, account_to, account_from, amount) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING transfer_id;";
        Long userID;
        String username;
        try {
            jdbcTemplate.update(sql, transfer.getTransferID(), transfer.getTransferTypeID(), transfer.getTransferStatusID(), transfer.getAccountTo(), transfer.getAccountFrom(), transfer.getAmount());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String sqlSender = "UPDATE account SET balance = balance - ?" +
                "WHERE account_id = ?;";
        try {
            jdbcTemplate.update(sqlSender, transfer.getAmount(), transfer.getAccountFrom());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String sqlReceiver = "UPDATE account SET balance = balance + ?" +
                "WHERE account_id = ?;";
        try {
            jdbcTemplate.update(sqlReceiver, transfer.getAmount(), transfer.getAccountTo());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
    @Override
    public Transfer getTransferDetails ( long transfer_id){
        Transfer transfer = null;
        String sql = "SELECT * from transfer WHERE transfer_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transfer_id);
        if (result.next()) {
            transfer = mapRowToTransfer(result);
        }
        return transfer;
    }

    @Override
    public List<Transfer> viewTransferHistory ( long user_id){
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT * FROM transfer WHERE user_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user_id);
        while (result.next()) {
            transfers.add(mapRowToTransfer(result));
        }
        return transfers;
    }

    private Transfer mapRowToTransfer (SqlRowSet rs){
        Transfer transfer = new Transfer();
        transfer.setTransferID(rs.getLong("transfer_id"));
        transfer.setTransferTypeID(rs.getLong("transfer_type_id"));
        transfer.setTransferStatusID(rs.getLong("transfer_status_id"));
        transfer.setAccountTo(rs.getLong("account_to"));
        transfer.setAccountFrom(rs.getLong("account_from"));
        transfer.setAmount(rs.getDouble("amount"));
        transfer.setUserID(rs.getLong("user_id"));
        transfer.setUsername(rs.getString("username"));
        return transfer;
    }
}
