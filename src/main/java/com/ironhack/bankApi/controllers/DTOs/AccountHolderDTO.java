package com.ironhack.bankApi.controllers.DTOs;

import com.ironhack.bankApi.models.AccountHolder;
import com.ironhack.bankApi.models.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountHolderDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;
    @Past
    private LocalDate dateOfBirth;
    @NotNull
    private Address primaryAddress;
    private Address mailingAddress;

    public AccountHolderDTO(String username, String password, String name, LocalDate dateOfBirth, Address primaryAddress) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
    }

    public AccountHolder toAccountHolder() {
        AccountHolder holder = new AccountHolder();
        holder.setUsername(username);
        holder.setPassword(password);
        holder.setName(name);
        holder.setDateOfBirth(dateOfBirth);
        holder.setPrimaryAddress(primaryAddress);
        holder.setMailingAddress(mailingAddress);
        return holder;
    }
}
