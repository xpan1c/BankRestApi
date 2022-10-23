package com.ironhack.bankApi.services.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.controllers.DTOs.CreditCardDTO;
import com.ironhack.bankApi.controllers.DTOs.SavingsDTO;
import com.ironhack.bankApi.models.*;

import java.util.List;

public interface AdminServiceInterface {
    List<User> getAllUsers();
    Account addCheckingAccount(AccountDTO account);
    CreditCard addCreditCard(CreditCardDTO creditCardDTO);
    Savings addCreditCard(SavingsDTO savingsDTO);
}
