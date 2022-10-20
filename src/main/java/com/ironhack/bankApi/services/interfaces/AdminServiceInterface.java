package com.ironhack.bankApi.services.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.models.Account;

public interface AdminServiceInterface {
    Account addCheckingAccount(AccountDTO account);
}
