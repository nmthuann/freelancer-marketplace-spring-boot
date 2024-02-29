package com.nmt.freelancermarketplacespringboot.services.users.account.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.account.IAccountRepository;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService
        extends AbstractBaseService <AccountEntity, String>
        implements IAccountService
{
    @Autowired
    IAccountRepository accountRepository;

    public AccountService(CrudRepository<AccountEntity, String> baseRepository) {
        super(baseRepository);
    }

//
//    public AccountEntity CreateOne() {
//
//    }
}
