package com.nmt.freelancermarketplacespringboot.services.auth.impl;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.AuthExceptionMessage;
import com.nmt.freelancermarketplacespringboot.common.utils.JwtServiceUtil;
import com.nmt.freelancermarketplacespringboot.dto.Payload;
import com.nmt.freelancermarketplacespringboot.dto.Tokens;
import com.nmt.freelancermarketplacespringboot.dto.auth.LoginDto;
import com.nmt.freelancermarketplacespringboot.dto.auth.RegisterDto;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.services.auth.IAuthService;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AuthService implements IAuthService, UserDetailsService {

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
    // public CompletableFuture<Tokens> login(LoginDto data) {
    public Tokens login (@NonNull LoginDto data) throws AuthException {

        AccountEntity findAccount = this.accountService.getOneById(data.email());

        if (findAccount != null && findAccount.isStatus()) {
            // fix Hash Pass
            Boolean checkPass = this.comparePassword(data.password(), findAccount.getPassword());

            if (!checkPass) {
                throw new AuthException(AuthExceptionMessage.PASSWORD_WRONG.getMessage());
            }
        } else {
            throw new AuthException(AuthExceptionMessage.LOGIN_INVALID.getMessage());
        }

        // ensure findAccount != null
        return this.jwtService.getTokens(new Payload(
                findAccount.getEmail(),
                findAccount.getRole().getRole_name(),
                findAccount.getSub()
        ));
    }


    @Override
    public CompletableFuture<Tokens> register (RegisterDto data) {
        return null;
    }


    @Override
    // from UserDetail interface
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AccountEntity account = this.accountService.getOneById(username);

        if (account != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    .password("{noop}password")
                    // {noop} để sử dụng mật khẩu không mã hóa (trong thực tế, bạn sẽ sử dụng mật khẩu từ account)
                    .roles(account.getRole().getRole_name())
                    .build();
        } else {
            throw new UsernameNotFoundException(AuthExceptionMessage.USERNAME_NOT_FOUND + username);
        }

    }
}




//
//        return CompletableFuture.supplyAsync(() -> {
//            AccountEntity findAccount = this.accountService.getOneById(data.email());
//
//            if (findAccount != null && findAccount.isStatus()) {
//                Boolean checkPass = this.comparePassword(data.password(), findAccount.getPassword());
//
//                if (!checkPass) {
//                    System.out.println(AuthException.PASSWORD_WRONG);
//                    // throw new InvalidException(AuthException.PASSWORD_WRONG);
//                }
//            } else {
//                System.out.println(AuthException.LOGIN_INVALID);
//                // throw err
//            }
//
//
//            // ensure findAccount != null
//            assert findAccount != null;
//            Tokens tokens = this.jwtService.getTokens(new Payload(
//                    findAccount.getEmail(),
//                    findAccount.getRole().getRole_name(),
//                    findAccount.getSub()
//            ));
//
//            System.out.println("tokens" + tokens);
//
//            return tokens;
//
//        });