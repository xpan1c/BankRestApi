package com.ironhack.bankApi.controllers;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.controllers.DTOs.CreditCardDTO;
import com.ironhack.bankApi.controllers.DTOs.SavingsDTO;
import com.ironhack.bankApi.controllers.interfaces.AdminControllerInterface;
import com.ironhack.bankApi.models.accounts.Account;
import com.ironhack.bankApi.models.accounts.CreditCard;
import com.ironhack.bankApi.models.accounts.Savings;
import com.ironhack.bankApi.models.users.User;
import com.ironhack.bankApi.services.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController implements AdminControllerInterface {
    @Autowired
    AdminServiceInterface adminService;
    @GetMapping("/api/admin/getUsers")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> getAllUsers(){
        return adminService.getAllUsers();
    }

    @PostMapping("/api/admin/newCheckingAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addCheckingAccount(@Valid @RequestBody AccountDTO accountDTO) {
        return adminService.addCheckingAccount(accountDTO);
    }
    @PostMapping("/api/admin/newCreditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard addCreditCard(@Valid @RequestBody CreditCardDTO creditCardDTO) {
        try {
            return adminService.addCreditCard(creditCardDTO);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Out of limits", e);
        }
    }
    @PostMapping("/api/admin/newSavings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings addSavings(@Valid @RequestBody SavingsDTO savingsDTO) {
        try {
            return adminService.addSavings(savingsDTO);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Out of limits", e);
        }
    }
}
