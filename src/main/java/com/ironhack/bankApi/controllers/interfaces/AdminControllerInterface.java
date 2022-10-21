package com.ironhack.bankApi.controllers.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.controllers.DTOs.CreditCardDTO;
import com.ironhack.bankApi.controllers.DTOs.SavingsDTO;
import com.ironhack.bankApi.models.Account;
import com.ironhack.bankApi.models.CreditCard;
import com.ironhack.bankApi.models.Savings;

public interface AdminControllerInterface {
    Account addCheckingAccount(AccountDTO accountDTO);
    CreditCard addCreditCard(CreditCardDTO creditCardDTO);
    Savings addSavings(SavingsDTO savingsDTO);
}
