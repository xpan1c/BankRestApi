package com.ironhack.bankApi.services.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountInformationDTO;
import com.ironhack.bankApi.models.users.AccountHolder;
import com.ironhack.bankApi.models.utils.TransferList;

import java.util.List;

public interface AccountHolderServiceInterface {
    AccountHolder addAccountHolder(AccountHolder accountHolder);
    List<AccountInformationDTO> getAccounts(String username);

    TransferList transference(Long id, Long fromId, Long toId, double quantity);
}
