package com.bank.kata.controller;

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

}