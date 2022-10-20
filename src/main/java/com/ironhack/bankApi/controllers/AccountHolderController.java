package com.ironhack.bankApi.controllers;

import com.ironhack.bankApi.controllers.DTOs.AccountHolderDTO;
import com.ironhack.bankApi.controllers.interfaces.AccountHolderControllerInterface;
import com.ironhack.bankApi.models.AccountHolder;
import com.ironhack.bankApi.services.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AccountHolderController implements AccountHolderControllerInterface {
    @Autowired
    AccountHolderServiceInterface accountHolderService;

    @PostMapping("/api/newAccountHolder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addAccountHolder(@Valid @RequestBody  AccountHolderDTO accountHolderDTO) {
        return accountHolderService.addAccountHolder(accountHolderDTO.toAccountHolder());
    }
    //@GetMapping("/accountHolder/getAccounts")
    //public getAccountsByAcountHolder(){

    //}

}
