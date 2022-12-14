package com.ironhack.bankApi.controllers.DTOs;

import com.ironhack.bankApi.models.users.ThirdParty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
@Data
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
