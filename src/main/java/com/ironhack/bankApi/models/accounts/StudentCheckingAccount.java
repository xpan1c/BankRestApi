package com.ironhack.bankApi.models.accounts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
//@PrimaryKeyJoinColumn(name = "scaId")
@Getter
@NoArgsConstructor
@Setter
public class StudentCheckingAccount extends Account{
}
