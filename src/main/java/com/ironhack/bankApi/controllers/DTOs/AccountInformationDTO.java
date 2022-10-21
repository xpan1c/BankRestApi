package com.ironhack.bankApi.controllers.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountInformationDTO {
    private String AccountType;
    private String balance;

    private String secretKey;
    private String primaryOwner;

    private String secondaryOwner;

    private String minimumBalance;

    private String penaltyFee;
    private String monthlyMaintenanceFee;
    private String status;
    private String creditLimit;
    private String interestRate;

    public AccountInformationDTO(String accountType, String balance, String secretKey, String primaryOwner, String secondaryOwner, String penaltyFee, String status) {
        AccountType = accountType;
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee = penaltyFee;
        this.status = status;
    }
    public AccountInformationDTO(String accountType, String balance, String secretKey, String primaryOwner, String secondaryOwner, String minimumBalance, String penaltyFee,  String status) {
        AccountType = accountType;
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.minimumBalance = minimumBalance;
        this.penaltyFee = penaltyFee;
        this.status = status;
    }
    public AccountInformationDTO(String accountType, String balance, String secretKey, String primaryOwner, String secondaryOwner, String minimumBalance, String penaltyFee, String monthlyMaintenanceFee, String status) {
        AccountType = accountType;
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.minimumBalance = minimumBalance;
        this.penaltyFee = penaltyFee;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
        this.status = status;
    }
}
