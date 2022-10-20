package com.ironhack.bankApi.models;

import com.ironhack.bankApi.models.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class StudentCheckingAccount extends Account{
}
