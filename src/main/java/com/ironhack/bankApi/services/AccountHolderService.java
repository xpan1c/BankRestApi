package com.ironhack.bankApi.services;

import com.ironhack.bankApi.controllers.DTOs.AccountInformationDTO;
import com.ironhack.bankApi.models.*;
import com.ironhack.bankApi.repositories.AccountHolderRepository;
import com.ironhack.bankApi.repositories.AccountRepository;
import com.ironhack.bankApi.services.interfaces.AccountHolderServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccountHolderService implements AccountHolderServiceInterface {
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;


    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    public List<AccountInformationDTO> getAccounts(Long id) {
        List<AccountInformationDTO> accounts = new ArrayList<>();
        AccountHolder accountHolder = accountHolderRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        for (CheckingAccount acc: accountRepository.findByMainCheckingAccountList(accountHolder)) {
            accounts.add(new AccountInformationDTO("Checking Account", acc.getBalance().toString(), String.valueOf(acc.getSecretKey()),
                    acc.getPrimaryOwner().getName(), Objects.requireNonNullElse(acc.getSecondaryOwner(), "none").toString(), new Money(acc.getMinimumBalance()).toString(),
                  new Money (acc.getPenaltyFee()).toString(),new Money(acc.getMonthlyMaintenanceFee()).toString(), acc.getStatus().toString()));
        }
        for (CreditCard acc: accountRepository.findByCreditCardList(accountHolder)) {
            accounts.add(new AccountInformationDTO("Credit Card", acc.getBalance().toString(),
                    String.valueOf(acc.getSecretKey()), acc.getPrimaryOwner().getName(), Objects.requireNonNullElse(acc.getSecondaryOwner(), "none").toString(),
                  new Money (acc.getPenaltyFee()).toString(), acc.getStatus().toString()));
        }
        for (Savings acc: accountRepository.findByMainSavingsAccountList(accountHolder)) {
            accounts.add(new AccountInformationDTO("Savings Account", acc.getBalance().toString(), String.valueOf(acc.getSecretKey()),
                    acc.getPrimaryOwner().getName(), Objects.requireNonNullElse(acc.getSecondaryOwner(), "none").toString(), new Money(acc.getMinimumBalance()).toString(),
                  new Money (acc.getPenaltyFee()).toString(), acc.getStatus().toString()));
        }
        for (StudentCheckingAccount acc: accountRepository.findByMainStudentCheckingAccountList(accountHolder)) {
            accounts.add(new AccountInformationDTO("Student Checking Account", acc.getBalance().toString(), String.valueOf(acc.getSecretKey()),
                    acc.getPrimaryOwner().getName(), Objects.requireNonNullElse(acc.getSecondaryOwner(), "none").toString(),
                  new Money (acc.getPenaltyFee()).toString(), acc.getStatus().toString()));
        }

        return accounts;
    }
}
