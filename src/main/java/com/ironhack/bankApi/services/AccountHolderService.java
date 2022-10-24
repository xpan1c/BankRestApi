package com.ironhack.bankApi.services;

import com.ironhack.bankApi.controllers.DTOs.AccountInformationDTO;
import com.ironhack.bankApi.models.accounts.*;
import com.ironhack.bankApi.models.users.AccountHolder;
import com.ironhack.bankApi.models.utils.Money;
import com.ironhack.bankApi.models.utils.TransferList;
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

    /**
     * Create a new Account  Holder
     * @return Saved accountHolder
     */
    public AccountHolder addAccountHolder(AccountHolder accountHolder) {
        return accountHolderRepository.save(accountHolder);
    }

    /**
     * Service to get All username account.
     * @param userName
     * @return A list of username's accounts.
     */
    public List<AccountInformationDTO> getAccounts(String userName) {
        AccountHolder accountHolder = accountHolderRepository.findByUsername(userName).get();
        List<AccountInformationDTO> accounts = new ArrayList<>();
        for (CheckingAccount acc: accountRepository.findByMainCheckingAccountList(accountHolder)) {
            accounts.add(new AccountInformationDTO(acc.getId(),"Checking Account", acc.getBalance().toString()));
        }
        for (CreditCard acc: accountRepository.findByCreditCardList(accountHolder)) {
            accounts.add(new AccountInformationDTO(acc.getId(),"Credit Card", acc.getBalance().toString()));
            acc.addInterest();
            accountRepository.save(acc);
        }
        for (Savings acc: accountRepository.findByMainSavingsAccountList(accountHolder)) {
            accounts.add(new AccountInformationDTO(acc.getId(),"Savings Account", acc.getBalance().toString()));
            acc.addInterest();
            accountRepository.save(acc);
        }
        for (StudentCheckingAccount acc: accountRepository.findByMainStudentCheckingAccountList(accountHolder)) {
            accounts.add(new AccountInformationDTO(acc.getId(),"Student Checking Account", acc.getBalance().toString()));
        }
        return accounts;
    }

    public TransferList transference(String usernameFrom, Long fromId, Long toId, double quantity) {
        if(getAccounts(usernameFrom).stream().anyMatch(accountInformationDTO -> accountInformationDTO.getId() == fromId)){
            Account from = accountRepository.findById(fromId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"The sender  given id does not exist"));
            Account to = accountRepository.findById(toId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"A receiver given id does not exist" ));
            TransferList transferList= new TransferList(from,to, BigDecimal.valueOf(quantity));
            accountRepository.save(from);
            accountRepository.save(to);
            return transferListRepository.save(transferList);
        }else{
            throw  new ResponseStatusException(HttpStatus.FORBIDDEN,"It's not sender account id");
        }
    }
}
