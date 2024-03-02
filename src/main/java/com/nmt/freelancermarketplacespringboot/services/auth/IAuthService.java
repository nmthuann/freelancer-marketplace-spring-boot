package com.nmt.freelancermarketplacespringboot.services.auth;

import com.nmt.freelancermarketplacespringboot.dto.Payload;
import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import com.nmt.freelancermarketplacespringboot.dto.auth.LoginDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterDto;

import java.util.concurrent.CompletableFuture;

public interface IAuthService {

    CompletableFuture<Tokens> getTokens(Payload payload);

    String hashPassword(String password);

    Boolean comparePassword(String password, String storePasswordHash);

    String randomPassword(int length, String base);

    CompletableFuture<Tokens> login(LoginDto data);

    CompletableFuture<Tokens> register(RegisterDto data);


}
