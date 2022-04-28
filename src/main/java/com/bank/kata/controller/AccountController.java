package com.bank.kata.controller;


import com.bank.kata.exception.OperationException;
import com.bank.kata.presentation.AccountPreview;
import com.bank.kata.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PatchMapping("/deposit/{amount}")
    private ResponseEntity<AccountPreview> deposit(@PathVariable Long amount)throws OperationException {

        AccountPreview accountPreview = accountService.deposit(amount);

        return new ResponseEntity<>(accountPreview, HttpStatus.CREATED);
    }

    @PatchMapping("/withdrawal/{amount}")
    private ResponseEntity<AccountPreview> withdrawal(@PathVariable  Long amount)throws OperationException {

        AccountPreview accountPreview = accountService.withdrawal(amount);

        return new ResponseEntity<>(accountPreview, HttpStatus.CREATED);
    }

}
