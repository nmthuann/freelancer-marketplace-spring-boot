package com.nmt.freelancermarketplacespringboot.services.auth.impl;

import com.nmt.freelancermarketplacespringboot.common.errors.exceptions.AuthException;
import com.nmt.freelancermarketplacespringboot.common.httpexceptions.InvalidException;
import com.nmt.freelancermarketplacespringboot.common.utils.JwtServiceUtil;
import com.nmt.freelancermarketplacespringboot.dto.Payload;
import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import com.nmt.freelancermarketplacespringboot.dto.auth.LoginDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterDto;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.services.auth.IAuthService;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthService implements IAuthService {
    // private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JwtServiceUtil jwtService;

    @Autowired
    IAccountService accountService;

    @Override
    public CompletableFuture<Tokens> getTokens(Payload payload) {
        return null;
    }

    @Override
    public String hashPassword(String password) {
        return null;
    }

    @Override
    public Boolean comparePassword(String password, String storePasswordHash) {
        return password.equals(storePasswordHash);
    }

    @Override
    public String randomPassword(int length, String base) {
        return null;
    }

    @Override
    public CompletableFuture<Tokens> login(LoginDto data) {
//        AccountEntity findAccount = this.accountService.getOneById(data.email());
//        if(findAccount != null && findAccount.isStatus()) {
//            Boolean checkPass = this.comparePassword(data.password(), findAccount.getPassword());
//            if(!checkPass){
//                // return Map.of("message", AuthException.PASSWORD_WRONG);
//                // throw new InvalidPasswordException(AuthExceptionMessages.PASSWORD_WRONG);
//                System.out.println(AuthException.PASSWORD_WRONG);
//                throw new InvalidException(AuthException.PASSWORD_WRONG);
//            }
//        }
//        else {
//            System.out.println(AuthException.LOGIN_INVALID);
//        }
//
//        // GetToken
//        Tokens tokens = this.jwtService.getTokens(
//                new Payload(
//                        findAccount.getEmail(),
//                        findAccount.getRole().getRole_name()
//                ));
//
//
//
//        return tokens;
        return CompletableFuture.supplyAsync(() -> {
            AccountEntity findAccount = this.accountService.getOneById(data.email());

            if (findAccount != null && findAccount.isStatus()) {
                Boolean checkPass = this.comparePassword(data.password(), findAccount.getPassword());

                if (!checkPass) {
                    System.out.println(AuthException.PASSWORD_WRONG);
                    // throw new InvalidException(AuthException.PASSWORD_WRONG);
                }
            } else {
                System.out.println(AuthException.LOGIN_INVALID);
                // Handle the case where the account is not found or has an invalid status
                // You might want to throw an exception or handle it according to your business logic
            }

            // Assuming this.jwtService.getTokens returns a CompletableFuture<Tokens>
            Tokens tokens = this.jwtService.getTokens(
                    new Payload(findAccount.getEmail(), findAccount.getRole().getRole_name()));

            System.out.println("tokens" + tokens);

            return tokens;

        });
    }

    @Override
    public CompletableFuture<Tokens> register(RegisterDto data) {
        return null;
    }
}
