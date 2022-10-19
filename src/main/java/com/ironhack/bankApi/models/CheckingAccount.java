package com.ironhack.bankApi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckingAccount extends StudentCheckingAccount{
    private BigDecimal minimumBalance;
    private BigDecimal montlyMantainanceFee;
}
