package com.nmt.freelancermarketplacespringboot.repositories.users.account;

import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends CrudRepository<AccountEntity, String> {

}
