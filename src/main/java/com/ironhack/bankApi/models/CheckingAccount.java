package com.ironhack.bankApi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.math.BigDecimal;
@Entity
//@PrimaryKeyJoinColumn(name = "caId")
@Getter
@Setter
public class CheckingAccount extends Account{
    private BigDecimal minimumBalance;
    private BigDecimal monthlyMaintenanceFee;

    public CheckingAccount() {
        setMinimumBalance(BigDecimal.valueOf(250.00));
        setMonthlyMaintenanceFee(BigDecimal.valueOf(12.00));
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    @Override
    public void decreaseBalance(BigDecimal decrease) {
        super.decreaseBalance(decrease);
        if(minimumBalance.compareTo(super.getBalance().getAmount().subtract(decrease)) >= 0){
            super.decreaseBalance(getPenaltyFee());
        }
    }
}
