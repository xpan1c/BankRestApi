package com.ironhack.bankApi.controllers;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.controllers.DTOs.CreditCardDTO;
import com.ironhack.bankApi.controllers.DTOs.NewThirdPartyDTO;
import com.ironhack.bankApi.controllers.DTOs.SavingsDTO;
import com.ironhack.bankApi.controllers.interfaces.AdminControllerInterface;
import com.ironhack.bankApi.models.accounts.Account;
import com.ironhack.bankApi.models.accounts.CreditCard;
import com.ironhack.bankApi.models.accounts.Savings;
import com.ironhack.bankApi.models.users.ThirdParty;
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
    @PostMapping("/api/v1/admin/newThirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty addThirdParty(NewThirdPartyDTO newThirdPartyDTO) {
        return adminService.addThirdParty(newThirdPartyDTO.toThirdParty());
    }

    @GetMapping("/api/v1/admin/getUsers")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<User> getAllUsers(){
        return adminService.getAllUsers();
    }

    @PostMapping("/api/v1/admin/newCheckingAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public Account addCheckingAccount(@Valid @RequestBody AccountDTO accountDTO) {
        return adminService.addCheckingAccount(accountDTO);
    }
    @PostMapping("/api/v1/admin/newCreditCard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard addCreditCard(@Valid @RequestBody CreditCardDTO creditCardDTO) {
            return adminService.addCreditCard(creditCardDTO);
    }
    @PostMapping("/api/v1/admin/newSavings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings addSavings(@Valid @RequestBody SavingsDTO savingsDTO) {
            return adminService.addSavings(savingsDTO);
    }
    @DeleteMapping(path = "/api/v1/admin/deleteAccount")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@RequestParam Long id){
        adminService.deleteAccount(id);
    }
}
