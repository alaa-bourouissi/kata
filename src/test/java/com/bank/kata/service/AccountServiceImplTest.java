package com.bank.kata.service;

import com.bank.kata.exception.OperationException;
import com.bank.kata.presentation.AccountPreview;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

}