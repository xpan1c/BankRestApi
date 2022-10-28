package com.ironhack.bankApi.controllers;

import com.ironhack.bankApi.controllers.DTOs.MoneyToAccountDTO;
import com.ironhack.bankApi.controllers.interfaces.ThirdPartyControllerInterface;
import com.ironhack.bankApi.models.utils.Money;
import com.ironhack.bankApi.models.utils.TransferList;
import com.ironhack.bankApi.services.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class ThirdPartyController implements ThirdPartyControllerInterface {
    @Autowired
    ThirdPartyServiceInterface thirdPartyService;
    @PutMapping("/api/thirdAccount/requestMoney")
    @ResponseStatus(HttpStatus.OK)
    public TransferList requestMoneyToAccount(@AuthenticationPrincipal UserDetails userDetails, @RequestHeader String hashKey, @RequestBody MoneyToAccountDTO moneyToAccountDTO){
        return thirdPartyService.requestMoneyToAccount(userDetails.getUsername(),hashKey,moneyToAccountDTO);
    }
    @PutMapping("/api/thirdAccount/sendMoney")
    @ResponseStatus(HttpStatus.OK)
    public TransferList sendMoneyToAccount(@AuthenticationPrincipal UserDetails userDetails,@RequestHeader String hashKey,@RequestBody MoneyToAccountDTO moneyToAccountDTO) {
        return thirdPartyService.sendMoneyToAccount(userDetails.getUsername(),hashKey,moneyToAccountDTO);
    }
}
