package com.nmt.freelancermarketplacespringboot.repositories.users.account;

import com.nmt.freelancermarketplacespringboot.entities.users.account.AuthMethodEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.account.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthMethodRepository extends CrudRepository<AuthMethodEntity, Integer> {
}
