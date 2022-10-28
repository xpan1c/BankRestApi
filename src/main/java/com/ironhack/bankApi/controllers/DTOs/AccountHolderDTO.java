package com.ironhack.bankApi.controllers.DTOs;

import com.ironhack.bankApi.models.users.AccountHolder;
import com.ironhack.bankApi.models.utils.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public AccountHolderDTO(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public AccountHolder toAccountHolder() {
        AccountHolder holder = new AccountHolder(username,password,name);
        holder.setDateOfBirth(dateOfBirth);
        holder.setPrimaryAddress(primaryAddress);
        holder.setMailingAddress(mailingAddress);
        return holder;
    }
}
