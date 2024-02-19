package com.nmt.freelancermarketplacespringboot.repositories.interfaces;

import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface IAccountRepository extends CrudRepository<AccountEntity, String> {
}
