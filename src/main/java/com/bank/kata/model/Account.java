package com.bank.kata.model;

import java.time.LocalDate;

public class Account {

    private String clientId;

    private String clientRib;

    private AccountHistory accountHistory;

    private LocalDate date;

    private Long balance;

    public Account() {
        this.balance= 0L;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
