package com.ironhack.bankApi.models;

import com.ironhack.bankApi.models.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private  Money balance;
    @Setter
    private int secretKey;
    @ManyToOne//(cascade = CascadeType.ALL)
    //@JoinColumn(name = "primary_id")
    @Setter
    private AccountHolder primaryOwner;
    @Setter
    @ManyToOne//(cascade = CascadeType.ALL)
    //@JoinColumn(name = "secondary_id")
    private AccountHolder secondaryOwner;
    @Setter
    private BigDecimal penaltyFee;
    @Setter
    private LocalDate creationDate;
    @Setter
    private Status status;

    public Account() {
        setPenaltyFee(BigDecimal.valueOf(40.00));
        setCreationDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        setStatus(Status.ACTIVE);
    }


    public Account(BigDecimal balance, int secretKey, AccountHolder primaryOwner, AccountHolder secondaryOwner, BigDecimal penaltyFee) {
        setBalance(balance);
        setSecretKey(secretKey);
        setPrimaryOwner(primaryOwner);
        setSecondaryOwner(secondaryOwner);
        setPenaltyFee(penaltyFee);
        setCreationDate(LocalDate.now(ZoneId.of("Europe/Paris")));
        setStatus(Status.ACTIVE);
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
