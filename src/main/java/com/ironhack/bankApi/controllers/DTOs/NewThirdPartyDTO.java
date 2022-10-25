package com.ironhack.bankApi.controllers.DTOs;

import com.ironhack.bankApi.models.users.ThirdParty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewThirdPartyDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String name;

    public ThirdParty toThirdParty(){
        return new ThirdParty(username,password,name);
    }
}
