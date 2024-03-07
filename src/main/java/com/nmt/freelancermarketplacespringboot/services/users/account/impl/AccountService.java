package com.nmt.freelancermarketplacespringboot.services.users.account.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.users.account.CreateAccountDto;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AuthMethodEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.account.RoleEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.account.IAccountRepository;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAuthMethodService;
import com.nmt.freelancermarketplacespringboot.services.users.account.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService
        extends AbstractBaseService <AccountEntity, String>
        implements IAccountService
{
    @Autowired
    IRoleService roleService;

    @Autowired
    IAuthMethodService authMethodService;

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    public AccountService(IAccountRepository accountRepository) {
        super(accountRepository);
    }

    @Override
    public AccountEntity createOne(CreateAccountDto data) {
        AuthMethodEntity authMethod = authMethodService.getOneById(data.getAuthMethod());
        RoleEntity role = roleService.getOneById(data.getRole());
        AccountEntity createAccount = new AccountEntity();
        createAccount.setEmail(data.getEmail());
        createAccount.setPassword(data.getPassword());
        createAccount.setStatus(data.isStatus());
        createAccount.setSub(data.getSub());
        createAccount.setRefreshToken(data.getRefreshToken());
        createAccount.setAuthMethod(authMethod);
        createAccount.setRole(role);
        this.accountRepository.save(createAccount);
        return createAccount;
    }



}
