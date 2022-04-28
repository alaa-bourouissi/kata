package com.bank.kata.service;

import com.bank.kata.exception.OperationException;
import com.bank.kata.model.Account;
import com.bank.kata.model.Transaction;
import com.bank.kata.model.TransactionType;
import com.bank.kata.presentation.AccountPreview;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AccountServiceImplTest {

    @Test
    public void deposit_ok() throws OperationException {
        AccountService accountService = new AccountServiceImpl();
        //given new account has balance = 0
        //when
        AccountPreview accountPreview = accountService.deposit(10L);
        //then
        assertNotNull(accountPreview);
        assertEquals(10,accountPreview.getBalance());
    }

    @Test
    public void deposit_exception_amount_negative() {
        AccountService accountService = new AccountServiceImpl();
        Exception exception = assertThrows(OperationException.class, () -> {
            accountService.deposit(-10L);;
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
        AccountPreview accountPreview = accountService.withdrawal(10L);
        //then
        assertNotNull(accountPreview);
        assertEquals(-10,accountPreview.getBalance());
    }

    @Test
    public void withdrawal_exception_plafond() {
        AccountService accountService = new AccountServiceImpl();
        Exception exception = assertThrows(OperationException.class, () -> {
            accountService.withdrawal(101L);;
        });
        String expectedMessage = "vous avez atteint le plafond de retrait autorisé";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void withdrawal_exception_amount_negative() {
        AccountService accountService = new AccountServiceImpl();
        Exception exception = assertThrows(OperationException.class, () -> {
            accountService.withdrawal(-10L);;
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
        accountService.deposit(100L);
        accountService.deposit(150L);
        accountService.withdrawal(50L);

        List<Transaction> transactions = accountService.getTransactions();
        //then
        assertNotNull(transactions);
        assertEquals(3,transactions.size());
        assertEquals(100,transactions.get(0).getAmount());
        assertEquals(100,transactions.get(0).getBalance());
        assertEquals(TransactionType.DEPOSIT,transactions.get(0).getType());

        assertEquals(150,transactions.get(1).getAmount());
        assertEquals(250,transactions.get(1).getBalance());
        assertEquals(TransactionType.DEPOSIT,transactions.get(1).getType());

        assertEquals(50,transactions.get(2).getAmount());
        assertEquals(200,transactions.get(2).getBalance());
        assertEquals(TransactionType.WITHDRAWAL,transactions.get(2).getType());
    }

}