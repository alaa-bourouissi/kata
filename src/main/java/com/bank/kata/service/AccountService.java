package com.bank.kata.service;

import com.bank.kata.exception.OperationException;
import com.bank.kata.model.Transaction;
import com.bank.kata.presentation.AccountPreview;

import java.util.List;

public interface AccountService {

    public AccountPreview deposit(Long amount) throws OperationException;

    public AccountPreview withdrawal(Long amount) throws OperationException;

    public List<Transaction> getTransactions();


}
