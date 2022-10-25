package com.ironhack.bankApi.controllers.DTOs;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MoneyToAccountDTO {
    @NotNull
    private Long id;
    @NotNull
    private int secretKey;
    @NotNull
    @Digits(fraction = 2,integer = 4)
    private double amount;
}
