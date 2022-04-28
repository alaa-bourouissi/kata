package com.bank.kata.service;

import com.bank.kata.exception.OperationException;
import com.bank.kata.mapper.AccountMapper;
import com.bank.kata.model.Account;
import com.bank.kata.model.Transaction;
import com.bank.kata.model.TransactionType;
import com.bank.kata.presentation.AccountPreview;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AccountServiceImpl implements AccountService{

    public   Account account = new Account();
    private static Long decouvert = 100L;

    @Override
    public AccountPreview deposit(Long amount) throws OperationException {
        if ((amount < 0)){
            throw new OperationException(OperationException.AMOUNT_MUST_NOT_BE_NEGATIVE);
        }
        LocalDate date =  LocalDate.now();
        Transaction transaction = new Transaction();

        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAmount(amount);
        transaction.setDate(date);

        account.setBalance(account.getBalance()+amount);
        transaction.setBalance(account.getBalance());
        account.setDate(date);
        account.getAccountHistory().getTransactions().add(transaction);
        AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

        return accountMapper.mapAccount(account);
    }
}
