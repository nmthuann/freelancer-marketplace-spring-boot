package com.nmt.freelancermarketplacespringboot.modules.account;

import com.nmt.freelancermarketplacespringboot.modules.account.entities.AccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface  AccountRepository extends CrudRepository<AccountEntity, String> {
}
