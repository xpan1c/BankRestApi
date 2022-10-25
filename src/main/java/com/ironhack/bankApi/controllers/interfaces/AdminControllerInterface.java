package com.ironhack.bankApi.controllers.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.controllers.DTOs.CreditCardDTO;
import com.ironhack.bankApi.controllers.DTOs.NewThirdPartyDTO;
import com.ironhack.bankApi.controllers.DTOs.SavingsDTO;
import com.ironhack.bankApi.models.accounts.Account;
import com.ironhack.bankApi.models.accounts.CreditCard;
import com.ironhack.bankApi.models.accounts.Savings;
import com.ironhack.bankApi.models.users.ThirdParty;
import com.ironhack.bankApi.models.users.User;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AdminControllerInterface {
    ThirdParty addThirdParty(NewThirdPartyDTO newThirdPartyDTO);
    List<User> getAllUsers();
    Account addCheckingAccount(AccountDTO accountDTO);
    CreditCard addCreditCard(CreditCardDTO creditCardDTO);
    Savings addSavings(SavingsDTO savingsDTO);
    void deleteAccount(Long id);
}
