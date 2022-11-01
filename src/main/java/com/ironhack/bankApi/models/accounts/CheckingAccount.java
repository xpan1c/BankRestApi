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
    private static final BigDecimal DEFAULT_MIN_BALANCE = new BigDecimal(250.00);
    private static final BigDecimal DEFAULT_MAINTENANCE_FEE = new BigDecimal(12.00);
    private BigDecimal monthlyMaintenanceFee;

    /**
     * Default minimum Balance 250.00 and MonthlyMaintenanceFee 12.00
     */
    public CheckingAccount() {
        setMinimumBalance(DEFAULT_MIN_BALANCE);
        setMonthlyMaintenanceFee(DEFAULT_MAINTENANCE_FEE);

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
