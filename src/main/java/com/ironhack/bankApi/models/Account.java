package com.ironhack.bankApi.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
public abstract class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    @Setter
    private Money amount;
    @Setter
    private String balance;
    @ManyToOne
    @Setter
    private AccountHolder accountHolder;
    @Setter
    @ManyToOne
    private AccountHolder secondaryOwner;
    @Setter
    private BigDecimal penaltyFee;

}
