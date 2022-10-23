package com.ironhack.bankApi.services;

import com.ironhack.bankApi.controllers.DTOs.AccountInformationDTO;
import com.ironhack.bankApi.models.*;
import com.ironhack.bankApi.repositories.AccountHolderRepository;
import com.ironhack.bankApi.repositories.AccountRepository;
import com.ironhack.bankApi.repositories.TransferListRepository;
import com.ironhack.bankApi.repositories.UserRepository;
import com.ironhack.bankApi.services.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccountHolderService implements AccountHolderServiceInterface {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TransferListRepository transferListRepository;
    @Autowired
    UserRepository userRepository;


    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    public List<AccountInformationDTO> getAccounts(String userName) {
        AccountHolder accountHolder = accountHolderRepository.findByUsername(userName).get();
        List<AccountInformationDTO> accounts = new ArrayList<>();
        for (CheckingAccount acc: accountRepository.findByMainCheckingAccountList(accountHolder)) {
            accounts.add(new AccountInformationDTO(acc.getId(),"Checking Account", acc.getBalance().toString(), String.valueOf(acc.getSecretKey()),
                    acc.getPrimaryOwner().getName(), Objects.requireNonNullElse(acc.getSecondaryOwner(), "none").toString(), new Money(acc.getMinimumBalance()).toString(),
                  new Money (acc.getPenaltyFee()).toString(),new Money(acc.getMonthlyMaintenanceFee()).toString(), acc.getStatus().toString()));
        }
        for (CreditCard acc: accountRepository.findByCreditCardList(accountHolder)) {
            accounts.add(new AccountInformationDTO(acc.getId(),"Credit Card", acc.getBalance().toString(),
                    String.valueOf(acc.getSecretKey()), acc.getPrimaryOwner().getName(), Objects.requireNonNullElse(acc.getSecondaryOwner(), "none").toString(),
                  new Money (acc.getPenaltyFee()).toString(), acc.getStatus().toString()));
        }
        for (Savings acc: accountRepository.findByMainSavingsAccountList(accountHolder)) {
            accounts.add(new AccountInformationDTO(acc.getId(),"Savings Account", acc.getBalance().toString(), String.valueOf(acc.getSecretKey()),
                    acc.getPrimaryOwner().getName(), Objects.requireNonNullElse(acc.getSecondaryOwner(), "none").toString(), new Money(acc.getMinimumBalance()).toString(),
                  new Money (acc.getPenaltyFee()).toString(), acc.getStatus().toString()));
        }
        for (StudentCheckingAccount acc: accountRepository.findByMainStudentCheckingAccountList(accountHolder)) {
            accounts.add(new AccountInformationDTO(acc.getId(),"Student Checking Account", acc.getBalance().toString(), String.valueOf(acc.getSecretKey()),
                    acc.getPrimaryOwner().getName(), Objects.requireNonNullElse(acc.getSecondaryOwner(), "none").toString(),
                  new Money (acc.getPenaltyFee()).toString(), acc.getStatus().toString()));
        }
        return accounts;
    }

    public TransferList transference(Long id, Long fromId, Long toId, double quantity) {
        AccountHolder accountHolder = accountHolderRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"This account holder id does not exist" ));
        Account from = accountRepository.findById(fromId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The sender  given id does not exist"));
        Account to = accountRepository.findById(toId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"A receiver given id does not exist" ));
        TransferList transferList= new TransferList(from,to, BigDecimal.valueOf(quantity));
        accountRepository.save(from);
        accountRepository.save(to);
        return transferListRepository.save(transferList);
    }
}
