package com.ironhack.bankApi.services.interfaces;

import com.ironhack.bankApi.controllers.DTOs.MoneyToAccountDTO;
import com.ironhack.bankApi.models.utils.Money;
import com.ironhack.bankApi.models.utils.TransferList;
import org.springframework.security.core.userdetails.UserDetails;

public interface ThirdPartyServiceInterface {
    TransferList requestMoneyToAccount(String username, String hashKey, MoneyToAccountDTO moneyToAccountDTO);
    TransferList sendMoneyToAccount(String username, String hashKey, MoneyToAccountDTO moneyToAccountDTO);
}
