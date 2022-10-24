package com.ironhack.bankApi.models.accounts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;
@Entity
//@PrimaryKeyJoinColumn(name = "caId")
@Getter
@Setter
public class CheckingAccount extends Account{
    private BigDecimal minimumBalance;
    private BigDecimal monthlyMaintenanceFee;

    /**
     * Default minimum Balance 250.00 and MonthlyMaintenanceFee 12.00
     */
    public CheckingAccount() {
        setMinimumBalance(BigDecimal.valueOf(250.00));
        setMonthlyMaintenanceFee(BigDecimal.valueOf(12.00));
    }

    /**
     * It applies penalty fee
     * @param decrease amount to Decrease Balance
     */
    @Override
    public void decreaseBalance(BigDecimal decrease) {
        super.decreaseBalance(decrease);
        if(minimumBalance.compareTo(super.getBalance().getAmount().subtract(decrease)) >= 0){
            super.decreaseBalance(getPenaltyFee());
        }
    }
}
