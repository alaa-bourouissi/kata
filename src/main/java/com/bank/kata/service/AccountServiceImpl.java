package com.bank.kata.service;

import com.bank.kata.exception.OperationException;
import com.bank.kata.mapper.AccountMapper;
import com.bank.kata.model.Account;
import com.bank.kata.model.Transaction;
import com.bank.kata.model.TransactionType;
import com.bank.kata.presentation.AccountPreview;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{

    public   Account account = new Account();
    private static BigDecimal decouvert = new BigDecimal(100);

    @Override
    public AccountPreview deposit(BigDecimal amount) throws OperationException {
        if ( amount.compareTo(BigDecimal.ZERO) < 0 ){
            throw new OperationException(OperationException.AMOUNT_MUST_NOT_BE_NEGATIVE);
        }
        LocalDate date =  LocalDate.now();
        Transaction transaction = new Transaction();

        transaction.setType(TransactionType.DEPOSIT);
        transaction.setAmount(amount);
        transaction.setDate(date);

        account.setBalance(account.getBalance().add(amount));
        transaction.setBalance(account.getBalance());
        account.getAccountHistory().getTransactions().add(transaction);
        AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

        return accountMapper.mapAccount(account);
    }

    @Override
    public AccountPreview withdrawal(BigDecimal amount) throws OperationException {
        BigDecimal actualBalance = account.getBalance().subtract(amount);
        Transaction transaction = new Transaction();
        AccountMapper accountMapper = Mappers.getMapper(AccountMapper.class);

        if (actualBalance.compareTo(decouvert.negate()) == -1 ){
            throw new OperationException(OperationException.SPENDING_LIMIT_REACHED);
        }else if ((amount.compareTo(BigDecimal.ZERO) < 0 )){
            throw new OperationException(OperationException.AMOUNT_MUST_NOT_BE_NEGATIVE);
        }else {

            LocalDate  date =  LocalDate.now();

            transaction.setType(TransactionType.WITHDRAWAL);
            transaction.setAmount(amount);
            transaction.setDate(date);
            transaction.setBalance(actualBalance);

            account.setBalance(actualBalance);
            account.getAccountHistory().getTransactions().add(transaction);
        }
        return accountMapper.mapAccount(account);
    }

    @Override
    public List<Transaction> getTransactions() {
        return account.getAccountHistory().getTransactions();
    }
}
