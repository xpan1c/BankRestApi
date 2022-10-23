package com.ironhack.bankApi.controllers.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.controllers.DTOs.CreditCardDTO;
import com.ironhack.bankApi.controllers.DTOs.SavingsDTO;
import com.ironhack.bankApi.models.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AdminControllerInterface {
    List<User> getAllUsers();
    Account addCheckingAccount(AccountDTO accountDTO);
    CreditCard addCreditCard(CreditCardDTO creditCardDTO);
    Savings addSavings(SavingsDTO savingsDTO);
}
