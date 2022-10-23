package com.ironhack.bankApi.controllers.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountInformationDTO {
    private Long id;
    private String accountType;
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

    public AccountInformationDTO(Long id, String accountType, String balance) {
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
    }

    public AccountInformationDTO(Long id, String accountType, String balance, String secretKey, String primaryOwner, String secondaryOwner, String penaltyFee, String status) {
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee = penaltyFee;
        this.status = status;
    }
    public AccountInformationDTO(Long id, String accountType, String balance, String secretKey, String primaryOwner, String secondaryOwner, String minimumBalance, String penaltyFee,  String status) {
        this.id = id;
        this.accountType = accountType;
        this.balance = balance;
        this.secretKey = secretKey;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.minimumBalance = minimumBalance;
        this.penaltyFee = penaltyFee;
        this.status = status;
    }
    public AccountInformationDTO(Long id, String accountType, String balance, String secretKey, String primaryOwner, String secondaryOwner, String minimumBalance, String penaltyFee, String monthlyMaintenanceFee, String status) {
        this.id = id;
        this.accountType = accountType;
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
