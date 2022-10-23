package com.ironhack.bankApi.controllers.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountHolderDTO;
import com.ironhack.bankApi.controllers.DTOs.AccountInformationDTO;
import com.ironhack.bankApi.models.users.AccountHolder;
import com.ironhack.bankApi.models.utils.TransferList;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AccountHolderControllerInterface {
    AccountHolder addAccountHolder(AccountHolderDTO AccountHolderDTO);
    List<AccountInformationDTO> getAccounts(UserDetails userDetails);
    TransferList transference(Long id,  Long fromId,  Long toId,  double quantity);
}
