package com.nmt.freelancermarketplacespringboot.services.auth.impl;

import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.AuthExceptionMessage;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    IAccountService accountService;

//    @Autowired
//    PasswordEncoder passwordEncoder;
//    private final PasswordEncoder passwordEncoder;
//
//    @Autowired
//    public UserDetailServiceImp(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity account = this.accountService.getOneById(username);
        if (account != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    // .password("{noop}password")
                    // .password(this.passwordEncoder.encode("password"))
                    .password(account.getPassword())
                    // {noop} để sử dụng mật khẩu không mã hóa (trong thực tế, bạn sẽ sử dụng mật khẩu từ account)
                    .roles(account.getRole().getRole_name())
                    .build();
        } else {
            throw new UsernameNotFoundException(AuthExceptionMessage.USERNAME_NOT_FOUND + username);
        }
    }
}



//@Override
//// from UserDetail interface
//public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//    AccountEntity account = this.accountService.getOneById(username);
//
//    if (account != null) {
//        return org.springframework.security.core.userdetails.User
//                .withUsername(username)
//                // .password("{noop}password")
//                .password(this.passwordEncoder.encode("password"))
//                // {noop} để sử dụng mật khẩu không mã hóa (trong thực tế, bạn sẽ sử dụng mật khẩu từ account)
//                .roles(account.getRole().getRole_name())
//                .build();
//    } else {
//        throw new UsernameNotFoundException(AuthExceptionMessage.USERNAME_NOT_FOUND + username);
//    }
//}