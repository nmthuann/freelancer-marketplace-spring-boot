package com.nmt.freelancermarketplacespringboot.services.auth;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;
import com.nmt.freelancermarketplacespringboot.dto.Payload;
import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import com.nmt.freelancermarketplacespringboot.dto.auth.LoginDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterResultDto;
import jakarta.validation.ConstraintViolationException;

import java.util.concurrent.CompletableFuture;

public interface IAuthService {

//    CompletableFuture<Tokens> getTokens(Payload payload);

    String hashPassword(String password);

    Boolean comparePassword(String password, String storePasswordHash);

    String randomPassword(int length, String base);

    // CompletableFuture<Tokens> login(LoginDto data);
    Tokens login (LoginDto data) throws AuthException, ConstraintViolationException;

    RegisterResultDto register(RegisterDto data);

    String verifyEmail(String email);


}
