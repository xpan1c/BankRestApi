package com.ironhack.bankApi.services.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.models.Account;
import com.ironhack.bankApi.models.CreditCard;
import com.ironhack.bankApi.models.Savings;

public interface AdminServiceInterface {
    Account addCheckingAccount(AccountDTO account);
    CreditCard addCreditCard(CreditCard creditCard);
    Savings addCreditCard(Savings savings);
}
