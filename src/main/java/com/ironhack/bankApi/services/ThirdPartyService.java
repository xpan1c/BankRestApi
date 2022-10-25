package com.ironhack.bankApi.services;

import com.ironhack.bankApi.controllers.DTOs.MoneyToAccountDTO;
import com.ironhack.bankApi.models.accounts.Account;
import com.ironhack.bankApi.models.utils.Money;
import com.ironhack.bankApi.models.utils.TransferList;
import com.ironhack.bankApi.repositories.AccountRepository;
import com.ironhack.bankApi.repositories.ThirdPartyRepository;
import com.ironhack.bankApi.repositories.TransferListRepository;
import com.ironhack.bankApi.services.interfaces.ThirdPartyServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class ThirdPartyService implements ThirdPartyServiceInterface {
    @Autowired
    ThirdPartyRepository thirdPartyRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TransferListRepository transferListRepository;
    public TransferList requestMoneyToAccount(String username, String hashKey, MoneyToAccountDTO moneyToAccountDTO) {
        if(!passwordEncoder.matches(thirdPartyRepository.findByUsername(username).get().getUsername(),hashKey)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong hashkey");
        }
        Account account = accountRepository.findById(moneyToAccountDTO.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "This account id does not exist"));
        if (account.getSecretKey() != moneyToAccountDTO.getSecretKey()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Secret Key does not match");
        }
        if (account.getBalance().getAmount().compareTo(BigDecimal.valueOf(moneyToAccountDTO.getAmount())) <= 0){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"the amount must be less than balance");
        }
        account.decreaseBalance(BigDecimal.valueOf(moneyToAccountDTO.getAmount()));
        TransferList transferList = new TransferList();
        transferList.setFrom(account);
        transferList.setQuantity(BigDecimal.valueOf(moneyToAccountDTO.getAmount()));
        return transferListRepository.save(transferList);
    }

    public TransferList sendMoneyToAccount(String username, String hashKey, MoneyToAccountDTO moneyToAccountDTO) {
        if(!passwordEncoder.matches(thirdPartyRepository.findByUsername(username).get().getUsername(),hashKey)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Wrong hashkey");
        }
        Account account = accountRepository.findById(moneyToAccountDTO.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "This account id does not exist"));
        if (account.getSecretKey() != moneyToAccountDTO.getSecretKey()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Secret Key does not match");
        }
        if (account.getBalance().getAmount().compareTo(BigDecimal.valueOf(moneyToAccountDTO.getAmount())) <= 0){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"the amount must be less than balance");
        }
        account.increaseBalance(BigDecimal.valueOf(moneyToAccountDTO.getAmount()));
        TransferList transferList = new TransferList();
        transferList.setTo(account);
        transferList.setQuantity(BigDecimal.valueOf(moneyToAccountDTO.getAmount()));
        return transferListRepository.save(transferList);
    }
}
