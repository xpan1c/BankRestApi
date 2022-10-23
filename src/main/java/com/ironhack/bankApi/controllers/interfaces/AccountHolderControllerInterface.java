package com.ironhack.bankApi.controllers.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountHolderDTO;
import com.ironhack.bankApi.controllers.DTOs.AccountInformationDTO;
import com.ironhack.bankApi.models.Account;
import com.ironhack.bankApi.models.AccountHolder;
import com.ironhack.bankApi.models.TransferList;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AccountHolderControllerInterface {
    AccountHolder addAccountHolder(AccountHolderDTO AccountHolderDTO);
    List<AccountInformationDTO> getAccounts(UserDetails userDetails);
    TransferList transference(Long id,  Long fromId,  Long toId,  double quantity);
}
