package com.nmt.freelancermarketplacespringboot.services.users.user.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.user.IUserRepository;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserService;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public class UserService extends AbstractBaseService<UserEntity, UUID> implements IUserService {
    public UserService(IUserRepository userRepository) {
        super(userRepository);
    }
}
