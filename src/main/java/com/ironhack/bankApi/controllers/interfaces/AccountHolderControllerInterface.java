package com.ironhack.bankApi.controllers.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountHolderDTO;
import com.ironhack.bankApi.controllers.DTOs.AccountInformationDTO;
import com.ironhack.bankApi.models.Account;
import com.ironhack.bankApi.models.AccountHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface AccountHolderControllerInterface {
    AccountHolder addAccountHolder(AccountHolderDTO AccountHolderDTO);
    List<AccountInformationDTO> getAccounts(Long id);
}
