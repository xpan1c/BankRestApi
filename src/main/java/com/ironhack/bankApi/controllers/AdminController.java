package com.ironhack.bankApi.controllers;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.controllers.interfaces.AdminControllerInterface;
import com.ironhack.bankApi.models.Account;
import com.ironhack.bankApi.services.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
