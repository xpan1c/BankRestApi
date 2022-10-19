package com.ironhack.bankApi.controllers.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountHolderDTO;
import com.ironhack.bankApi.models.AccountHolder;
import org.springframework.http.ResponseEntity;

public interface AccountHolderControllerInterface {
    AccountHolder addAccountHolder(AccountHolderDTO AccountHolderDTO);
}
