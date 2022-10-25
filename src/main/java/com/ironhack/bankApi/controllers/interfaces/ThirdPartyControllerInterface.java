package com.ironhack.bankApi.controllers.interfaces;

import com.ironhack.bankApi.controllers.DTOs.MoneyToAccountDTO;
import com.ironhack.bankApi.models.utils.Money;
import com.ironhack.bankApi.models.utils.TransferList;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface ThirdPartyControllerInterface {
    TransferList requestMoneyToAccount(UserDetails userDetails, String hashKey, MoneyToAccountDTO moneyToAccountDTO);
    TransferList sendMoneyToAccount(UserDetails userDetails, String hashKey, MoneyToAccountDTO moneyToAccountDTO);
}
