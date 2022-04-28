package com.bank.kata.controller;

import com.bank.kata.model.Transaction;
import com.bank.kata.model.TransactionType;
import com.bank.kata.presentation.AccountPreview;
import com.bank.kata.service.AccountService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void deposit() throws Exception {
        AccountPreview accountPreview = new AccountPreview();
        accountPreview.setDate(LocalDate.parse("2022-01-01"));
        accountPreview.setBalance(150L);
        Mockito.when(
                accountService.deposit(Mockito.anyLong())).thenReturn(accountPreview);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/account/deposit/10")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isCreated())
                .andExpect(jsonPath("$.balance", Matchers.is(150)))
                .andExpect(jsonPath("$.date", Matchers.is("2022-01-01")));
    }

    @Test
    public void withdrawal() throws Exception {
        AccountPreview accountPreview = new AccountPreview();
        accountPreview.setDate(LocalDate.parse("2022-01-01"));
        accountPreview.setBalance(100L);
        Mockito.when(
                accountService.withdrawal(Mockito.anyLong())).thenReturn(accountPreview);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/account/withdrawal/10")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().isCreated())
                .andExpect(jsonPath("$.balance", Matchers.is(100)))
                .andExpect(jsonPath("$.date", Matchers.is("2022-01-01")));
    }

    @Test
    public void getAllTransactions() throws Exception {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction();
        transaction1.setAmount(10L);
        transaction1.setBalance(10L);
        transaction1.setType(TransactionType.DEPOSIT);
        transaction1.setDate(LocalDate.now());
        transactions.add(transaction1);

        Mockito.when(
                accountService.getTransactions()).thenReturn(transactions);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/account/transactions")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].type", Matchers.is("DEPOSIT")))
                .andExpect(jsonPath("$[0].amount", Matchers.is(10)))
                .andExpect(jsonPath("$[0].balance", Matchers.is(10)));
    }

}