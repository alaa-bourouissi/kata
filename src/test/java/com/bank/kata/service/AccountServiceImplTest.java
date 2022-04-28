package com.bank.kata.service;

import com.bank.kata.exception.OperationException;
import com.bank.kata.model.Account;
import com.bank.kata.model.Transaction;
import com.bank.kata.model.TransactionType;
import com.bank.kata.presentation.AccountPreview;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AccountServiceImplTest {

    @Test
    public void deposit_ok() throws OperationException {
        AccountService accountService = new AccountServiceImpl();
        //given new account has balance = 0
        //when
        AccountPreview accountPreview = accountService.deposit(new BigDecimal(10));
        //then
        assertNotNull(accountPreview);
        assertEquals(new BigDecimal(10),accountPreview.getBalance());
    }

    @Test
    public void deposit_exception_amount_negative() {
        AccountService accountService = new AccountServiceImpl();
        Exception exception = assertThrows(OperationException.class, () -> {
            accountService.deposit(new BigDecimal(10).negate());;
        });

        String expectedMessage = "Le montant doit etre positif";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void withdrawal_ok() throws OperationException{
        AccountService accountService = new AccountServiceImpl();
        //given new account has balance = 0
        //when
        AccountPreview accountPreview = accountService.withdrawal(new BigDecimal(10));
        //then
        assertNotNull(accountPreview);
        assertEquals(new BigDecimal(10).negate(),accountPreview.getBalance());
    }

    @Test
    public void withdrawal_exception_plafond() {
        AccountService accountService = new AccountServiceImpl();
        Exception exception = assertThrows(OperationException.class, () -> {
            accountService.withdrawal(new BigDecimal(101));;
        });
        String expectedMessage = "vous avez atteint le plafond de retrait autorisé";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void withdrawal_exception_amount_negative() {
        AccountService accountService = new AccountServiceImpl();
        Exception exception = assertThrows(OperationException.class, () -> {
            accountService.withdrawal(new BigDecimal(10).negate());;
        });
        String expectedMessage = "Le montant doit etre positif";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void allTransactions() throws OperationException{
        AccountService accountService = new AccountServiceImpl();

        Account account = new Account();
        //given new account has balance = 0
        //when
        accountService.deposit(new BigDecimal(100));
        accountService.deposit(new BigDecimal(150));
        accountService.withdrawal(new BigDecimal(50));

        List<Transaction> transactions = accountService.getTransactions();
        //then
        assertNotNull(transactions);
        assertEquals(3,transactions.size());
        assertEquals(new BigDecimal(100),transactions.get(0).getAmount());
        assertEquals(new BigDecimal(100),transactions.get(0).getBalance());
        assertEquals(TransactionType.DEPOSIT,transactions.get(0).getType());

        assertEquals(new BigDecimal(150),transactions.get(1).getAmount());
        assertEquals(new BigDecimal(250),transactions.get(1).getBalance());
        assertEquals(TransactionType.DEPOSIT,transactions.get(1).getType());

        assertEquals(new BigDecimal(50),transactions.get(2).getAmount());
        assertEquals(new BigDecimal(200),transactions.get(2).getBalance());
        assertEquals(TransactionType.WITHDRAWAL,transactions.get(2).getType());
    }

}