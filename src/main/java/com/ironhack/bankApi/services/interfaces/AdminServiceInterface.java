package com.ironhack.bankApi.services.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.controllers.DTOs.CreditCardDTO;
import com.ironhack.bankApi.controllers.DTOs.SavingsDTO;
import com.ironhack.bankApi.models.accounts.Account;
import com.ironhack.bankApi.models.accounts.CreditCard;
import com.ironhack.bankApi.models.accounts.Savings;
import com.ironhack.bankApi.models.users.User;

import java.util.List;

public interface AdminServiceInterface {
    List<User> getAllUsers();
    Account addCheckingAccount(AccountDTO account);
    CreditCard addCreditCard(CreditCardDTO creditCardDTO);
    Savings addSavings(SavingsDTO savingsDTO);
}
