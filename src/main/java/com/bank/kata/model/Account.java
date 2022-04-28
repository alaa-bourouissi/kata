package com.bank.kata.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

    private String clientId;

    private String clientRib;

    private AccountHistory accountHistory;

    private BigDecimal balance;

    public Account() {
        this.balance= new BigDecimal(0);
        this.accountHistory = new AccountHistory();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientRib() {
        return clientRib;
    }

    public void setClientRib(String clientRib) {
        this.clientRib = clientRib;
    }

    public AccountHistory getAccountHistory() {
        return accountHistory;
    }

    public void setAccountHistory(AccountHistory accountHistory) {
        this.accountHistory = accountHistory;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
