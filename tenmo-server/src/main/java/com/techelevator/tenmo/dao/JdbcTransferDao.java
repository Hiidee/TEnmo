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
    public boolean createTransfer(Long currentUser, Transfer transfer) {
        String sql = "INSERT INTO transfer (transfer_id, transfer_type_id, transfer_status_id, account_to, account_from, amount) " +
                "VALUES (default, 2, 2, (SELECT account_id FROM account WHERE user_id = ?), " +
                "(SELECT account_id FROM account WHERE user_id = ?), ?) RETURNING transfer_id;";
        try {
            jdbcTemplate.update(sql, transfer.getUserID(), currentUser, transfer.getAmount());
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sqlSender = "UPDATE account SET balance = balance - ?" +
                "WHERE user_id = ?;";
        try {
            jdbcTemplate.update(sqlSender, transfer.getAmount(), currentUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sqlReceiver = "UPDATE account SET balance = balance + ?" +
                "WHERE user_id = ?;";
        try {
            jdbcTemplate.update(sqlReceiver, transfer.getAmount(), transfer.getUserID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Transfer getTransferDetails (long transfer_id) {
        Transfer transfer = null;
        String sql = "SELECT * from transfer WHERE transfer_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transfer_id);
        if (result.next()) {
            transfer = mapRowToTransfer(result);
        }
        return transfer;
    }

    @Override
    public List<Transfer> getTransferHistory (long user_id) {
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
        transfer.setAccountID(rs.getLong("account_id"));
        return transfer;
    }
}
