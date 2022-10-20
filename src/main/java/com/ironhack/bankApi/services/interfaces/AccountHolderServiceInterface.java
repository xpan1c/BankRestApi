package com.ironhack.bankApi.services.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountHolderDTO;
import com.ironhack.bankApi.models.AccountHolder;

import java.util.Optional;

public interface AccountHolderServiceInterface {
    AccountHolder addAccountHolder(AccountHolder accountHolder);
}
