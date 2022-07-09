package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountDao {
    List<Account> getAllAccounts();

    Account findByUserID(long id);

    BigDecimal getBalance(long id);

    BigDecimal addToBalance(BigDecimal amountToAdd, long id);

    BigDecimal subtractFromBalance(BigDecimal amountToSubtract, long id);
}
