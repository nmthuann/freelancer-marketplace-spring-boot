package com.nmt.freelancermarketplacespringboot.services.auth.impl;

import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.users.AuthExceptionMessages;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity account = this.accountService.getOneById(username);
        if (account != null) {
//            String[] roles = {account.getRole().getRole_name()};
//            List<GrantedAuthority> authorities = Collections.singletonList(
//                    new SimpleGrantedAuthority("ROLE_" + account.getRole().getRole_name())
//            );
            return org.springframework.security.core.userdetails.User
                    .withUsername(username)
                    // .password("{noop}password") sử dụng mật khẩu không mã hóa
                    .password(account.getPassword())
                    // .roles(account.getRole().getRole_name())
                    .roles(account.getRole().getRole_name())
                    .build();
        } else {
            throw new UsernameNotFoundException(AuthExceptionMessages.USERNAME_NOT_FOUND + username);
        }
    }
}