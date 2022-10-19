package com.ironhack.bankApi.services;

import com.ironhack.bankApi.models.AccountHolder;
import com.ironhack.bankApi.repositories.AccountHolderRepository;
import com.ironhack.bankApi.services.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class AccountHolderService implements AccountHolderServiceInterface {
    @Autowired
    AccountHolderRepository accountHolderRepository;


    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }
}
