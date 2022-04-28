package com.bank.kata.presentation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountPreview {


    private BigDecimal balance;

    public AccountPreview() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
