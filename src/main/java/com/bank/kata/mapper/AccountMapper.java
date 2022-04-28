package com.bank.kata.mapper;

import com.bank.kata.model.Account;
import com.bank.kata.presentation.AccountPreview;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {

    public AccountPreview mapAccount(Account account);
}
