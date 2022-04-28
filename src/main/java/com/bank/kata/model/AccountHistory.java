package com.bank.kata.model;

import java.util.ArrayList;
import java.util.List;

public class AccountHistory {

    private List<Transaction> transactions;

    public AccountHistory() {
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
