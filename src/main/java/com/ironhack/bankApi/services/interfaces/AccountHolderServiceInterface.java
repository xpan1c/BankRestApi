package com.ironhack.bankApi.services.interfaces;

import com.ironhack.bankApi.controllers.DTOs.AccountInformationDTO;
import com.ironhack.bankApi.models.users.AccountHolder;
import com.ironhack.bankApi.models.utils.TransferList;

import java.util.List;

public interface AccountHolderServiceInterface {
    /**
     * Service to create a new Account  Holder
     * @return Saved accountHolder
     */
    AccountHolder addAccountHolder(AccountHolder accountHolder);
    /**
     * Service to get All username account.
     * @return A list of username's accounts.
     */
    List<AccountInformationDTO> getAccounts(String username);

    /**
     *  Service to transfer from username account to another account
     * @param userNameFrom
     * @param fromId username ids
     * @param toId
     * @param quantity
     * @return
     */
    TransferList transference(String userNameFrom, Long fromId, Long toId, double quantity);
}
