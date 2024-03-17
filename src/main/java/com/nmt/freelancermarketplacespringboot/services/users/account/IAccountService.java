package com.nmt.freelancermarketplacespringboot.services.users.account;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.users.account.CreateAccountDto;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;

public interface IAccountService extends IBaseService <AccountEntity, String> {
    AccountEntity createOne(CreateAccountDto data);
    AccountEntity updateRefreshToken(String email);
}
