package com.ironhack.bankApi.services.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.controllers.DTOs.CreditCardDTO;
import com.ironhack.bankApi.controllers.DTOs.SavingsDTO;
import com.ironhack.bankApi.models.Account;
import com.ironhack.bankApi.models.CreditCard;
import com.ironhack.bankApi.models.Savings;

public interface AdminServiceInterface {
    Account addCheckingAccount(AccountDTO account);
    CreditCard addCreditCard(CreditCardDTO creditCardDTO);
    Savings addCreditCard(SavingsDTO savingsDTO);
}
