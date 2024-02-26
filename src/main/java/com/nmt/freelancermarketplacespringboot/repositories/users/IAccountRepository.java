package com.nmt.freelancermarketplacespringboot.repositories.users;

import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends CrudRepository<AccountEntity, String> {
}
