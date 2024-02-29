package com.nmt.freelancermarketplacespringboot.services.users.account.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.account.RoleEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.IAccountRepository;
import com.nmt.freelancermarketplacespringboot.repositories.users.IRoleRepository;
import com.nmt.freelancermarketplacespringboot.services.users.account.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class RoleService extends AbstractBaseService<RoleEntity, Integer> implements IRoleService {
    @Autowired
    IRoleRepository roleRepository;

    public RoleService(CrudRepository<RoleEntity, Integer> baseRepository) {
        super(baseRepository);
    }


    @Override
    public void softDeleteRole(Integer id) {
        Date now = new Date();
        Optional<RoleEntity> findRoleById = roleRepository.findById(id);
        findRoleById.ifPresent(role -> {
            role.setDeletedAt(now);
            roleRepository.save(role);
        });
    }
}
