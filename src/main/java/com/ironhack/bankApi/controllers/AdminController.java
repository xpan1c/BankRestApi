package com.ironhack.bankApi.controllers;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.controllers.DTOs.CreditCardDTO;
import com.ironhack.bankApi.controllers.DTOs.SavingsDTO;
import com.ironhack.bankApi.controllers.interfaces.AdminControllerInterface;
import com.ironhack.bankApi.models.Account;
import com.ironhack.bankApi.models.CreditCard;
import com.ironhack.bankApi.models.Savings;
import com.ironhack.bankApi.services.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class AdminController implements AdminControllerInterface {
    @Autowired
    AdminServiceInterface adminService;

    @PostMapping("/api/admin/newCheckingAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addCheckingAccount(@Valid @RequestBody AccountDTO accountDTO) {
        return adminService.addCheckingAccount(accountDTO);
    }
    @PostMapping("/api/admin/newCreditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard addCreditCard(@Valid @RequestBody CreditCardDTO creditCardDTO) {
        try {
            return adminService.addCreditCard(creditCardDTO.toCreditCard());
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Out of limits", e);
        }
    }
    @PostMapping("/api/admin/newSavings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings addSavings(@Valid @RequestBody SavingsDTO savingsDTO) {
        try {
            return adminService.addCreditCard(savingsDTO.toSavings());
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Out of limits", e);
        }
    }
}
