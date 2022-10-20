package com.ironhack.bankApi.controllers.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.models.Account;

public interface AdminControllerInterface {
    Account addCheckingAccount(AccountDTO accountDTO);
}
