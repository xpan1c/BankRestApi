package com.ironhack.bankApi.services;

import com.ironhack.bankApi.controllers.DTOs.AccountHolderDTO;
import com.ironhack.bankApi.controllers.DTOs.AccountInformationDTO;
import com.ironhack.bankApi.models.accounts.*;
import com.ironhack.bankApi.models.enums.Status;
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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Create a new Account  Holder, and encrypt Password
     * @return Saved accountHolder
     */
    public AccountHolder addAccountHolder(AccountHolderDTO accountHolderDTO) {
        if(userRepository.findByUsername(accountHolderDTO.getUsername()).isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This username already exist");
        }
        AccountHolder accountHolder = accountHolderDTO.toAccountHolder();
        accountHolder.setPassword(passwordEncoder.encode(accountHolderDTO.getPassword()));
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
        BigDecimal amount = new BigDecimal(quantity);
        if(getAccounts(usernameFrom).stream().anyMatch(accountInformationDTO -> accountInformationDTO.getId() == fromId)){
            Account from = accountRepository.findById(fromId).orElseThrow(()->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,"The sender  given id does not exist"));
            Account to = accountRepository.findById(toId).orElseThrow(()->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,"A receiver given id does not exist" ));
            if(from.getStatus().compareTo(Status.FROZEN) == 0)
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,"This account is FROZEN");
            if(from.getBalance().getAmount().compareTo(amount) >= 0){
                try{
                TransferList transferList= new TransferList(from,to, amount);
                from.decreaseBalance(amount);
                to.increaseBalance(amount);
                accountRepository.save(from);
                accountRepository.save(to);
                return transferListRepository.save(transferList);
                }catch (IllegalArgumentException e){
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Transfer did not take place",e);
                }

            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Not enough founds");
            }
        }else{
            throw  new ResponseStatusException(HttpStatus.FORBIDDEN,"It's not sender account id");
        }
    }
}
