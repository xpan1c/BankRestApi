package com.ironhack.bankApi.controllers;

import com.ironhack.bankApi.controllers.DTOs.AccountHolderDTO;
import com.ironhack.bankApi.controllers.DTOs.AccountInformationDTO;
import com.ironhack.bankApi.controllers.interfaces.AccountHolderControllerInterface;
import com.ironhack.bankApi.models.AccountHolder;
import com.ironhack.bankApi.models.TransferList;
import com.ironhack.bankApi.services.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AccountHolderController implements AccountHolderControllerInterface {
    @Autowired
    AccountHolderServiceInterface accountHolderService;

    @PostMapping("/api/newAccountHolder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder addAccountHolder(@Valid @RequestBody  AccountHolderDTO accountHolderDTO) {
        return accountHolderService.addAccountHolder(accountHolderDTO.toAccountHolder());
    }
    @GetMapping("/api/accountHolder/{id}/getAccounts")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<AccountInformationDTO> getAccounts(@PathVariable Long id){
        return accountHolderService.getAccounts(id);
    }
    @PostMapping("/api/accountHolder/{id}/transference")
    @ResponseStatus(HttpStatus.OK)
    public TransferList transference(@PathVariable Long id, @RequestParam Long from, @RequestParam Long to, @RequestParam double quantity){
       return accountHolderService.transference(id,from,to,quantity);
    }

}
