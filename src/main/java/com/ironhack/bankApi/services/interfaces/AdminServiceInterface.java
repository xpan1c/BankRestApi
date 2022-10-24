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
    /**
     *
     * @return List of all users
     */
    List<User> getAllUsers();

    /**
     * Create new checking Account, if age is below 24 creates a Student checking account.
     * @param account
     * @return
     */
    Account addCheckingAccount(AccountDTO account);

    /**
     * Create new credit card account
     * @param creditCardDTO
     * @return
     */
    CreditCard addCreditCard(CreditCardDTO creditCardDTO);

    /**
     * Creates a new saving account
     * @param savingsDTO
     * @return
     */
    Savings addSavings(SavingsDTO savingsDTO);
}
