package com.ironhack.bankApi.services;

import com.ironhack.bankApi.controllers.DTOs.AccountDTO;
import com.ironhack.bankApi.controllers.DTOs.CreditCardDTO;
import com.ironhack.bankApi.controllers.DTOs.SavingsDTO;
import com.ironhack.bankApi.models.*;
import com.ironhack.bankApi.repositories.*;
import com.ironhack.bankApi.services.interfaces.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class AdminService implements AdminServiceInterface {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    AccountRepository accountRepository;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    /**
     * Service to add new Checking Account. Creates a Student Checking Account if Primary Owner age is less than 24, optionally add a secondary owner.
     **/
    public Account addCheckingAccount(AccountDTO accountDTO) {
        AccountHolder primaryOwner = accountHolderRepository.findById(accountDTO.getPrimaryOwner()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(ChronoUnit.YEARS.between(primaryOwner.getDateOfBirth(), LocalDate.now()) < 24){
            StudentCheckingAccount studentCheckingAccount = accountDTO.toStudentCheckingAccount();
            studentCheckingAccount.setPrimaryOwner(primaryOwner);
            if(accountDTO.getSecondaryOwner() != null) {
                studentCheckingAccount.setSecondaryOwner(accountHolderRepository.findById(accountDTO.getSecondaryOwner()).get());
            }
            return accountRepository.save(studentCheckingAccount);
        }else{
            CheckingAccount checkingAccount = accountDTO.toCheckingAccount();
            checkingAccount.setPrimaryOwner(primaryOwner);
            if(accountDTO.getSecondaryOwner() != null) {
                checkingAccount.setSecondaryOwner(accountHolderRepository.findById(accountDTO.getSecondaryOwner()).get());
            }
            return accountRepository.save(checkingAccount);
        }
    }

    public CreditCard addCreditCard(CreditCardDTO creditCardDTO) {
        AccountHolder primaryOwner = accountHolderRepository.findById(creditCardDTO.getPrimaryOwner()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        CreditCard creditCard = creditCardDTO.toCreditCard();
        creditCard.setPrimaryOwner(primaryOwner);
        if(creditCardDTO.getSecondaryOwner() != null) {
            creditCard.setSecondaryOwner(accountHolderRepository.findById(creditCardDTO.getSecondaryOwner()).get());
        }
        return accountRepository.save(creditCard);
    }

    public Savings addCreditCard(SavingsDTO savingsDTO) {
        AccountHolder primaryOwner = accountHolderRepository.findById(savingsDTO.getPrimaryOwner()).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        Savings savings = savingsDTO.toSavings();
        savings.setPrimaryOwner(primaryOwner);
        if(savingsDTO.getSecondaryOwner() != null) {
            savings.setSecondaryOwner(accountHolderRepository.findById(savingsDTO.getSecondaryOwner()).get());
        }
        return accountRepository.save(savings);
    }

}
