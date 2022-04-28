package com.bank.kata.service;

import com.bank.kata.exception.OperationException;
import com.bank.kata.model.Transaction;
import com.bank.kata.presentation.AccountPreview;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    public AccountPreview deposit(BigDecimal amount) throws OperationException;

    public AccountPreview withdrawal(BigDecimal amount) throws OperationException;

    public List<Transaction> getTransactions();


}
