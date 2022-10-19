package com.ironhack.bankApi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Transient
    @Setter
    private  Money balance;
    @ManyToOne
    @Setter
    private AccountHolder accountHolder;
    @Setter
    @ManyToOne
    private AccountHolder secondaryOwner;
    @Setter
    private BigDecimal penaltyFee;

    public Account() {
        setPenaltyFee(BigDecimal.valueOf(40.00));
    }

    public Account(BigDecimal balance, AccountHolder accountHolder, AccountHolder secondaryOwner, BigDecimal penaltyFee) {
        setBalance(balance);
        setAccountHolder(accountHolder);
        setSecondaryOwner(secondaryOwner);
        setPenaltyFee(penaltyFee);
    }

    public void setBalance(BigDecimal balance) {
        this.balance = new Money(balance);
    }
    public void decreaseBalance(BigDecimal decrease){
        balance.decreaseAmount(decrease);
    }

    public void increaseBalance(BigDecimal increase){
        balance.increaseAmount(increase);
    }

}
