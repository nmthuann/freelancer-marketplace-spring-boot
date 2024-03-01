package com.nmt.freelancermarketplacespringboot.dto.users.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
// @JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountDto  {
//    @NotEmpty
//    @Email
    private String email;
    private String password;
    private boolean status;
    private String refreshToken;
    private String sub;
    private Integer role;

    private Integer authMethod;


    // Default constructor
    public CreateAccountDto() {
    }

    // Parameterized constructor
    public CreateAccountDto(
            String email,
            boolean status,
            String refreshToken,
            String password,
            Integer role,
            Integer authMethod,
            String sub
    ) {
        this.email = email;
        this.status = status;
        this.refreshToken = refreshToken;
        this.password = password;
        this.role = role;
        this.authMethod = authMethod;
        this.sub = sub;
    }
}
