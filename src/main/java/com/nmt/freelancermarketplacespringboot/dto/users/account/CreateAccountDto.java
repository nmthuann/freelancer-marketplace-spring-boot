package com.nmt.freelancermarketplacespringboot.dto.users.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Data
// @JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountDto  {

    @Email(message = "Email invalid!")
    private String email;

    @NotEmpty(message = "password is empty!")
    @Min(value = 8, message = "Password 8 character!")
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
