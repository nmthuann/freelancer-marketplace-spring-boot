package com.nmt.freelancermarketplacespringboot.services.auth;

import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import com.nmt.freelancermarketplacespringboot.dto.auth.LoginDto;

import java.util.concurrent.CompletableFuture;

public interface IAuthService {
    String hashPassword(String password);
    Boolean comparePassword(String password, String storePasswordHash);
    String randomPassword(int length, String base);

    CompletableFuture<Tokens> login(LoginDto data);


}
