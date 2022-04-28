package com.bank.kata.presentation;

import java.time.LocalDate;

public class AccountPreview {

    private LocalDate date;

    private Long balance;

    public AccountPreview() {
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
