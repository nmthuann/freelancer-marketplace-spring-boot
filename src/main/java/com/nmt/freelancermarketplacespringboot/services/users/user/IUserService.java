package com.nmt.freelancermarketplacespringboot.services.users.user;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;

import java.util.UUID;

public interface IUserService extends IBaseService<UserEntity, UUID> {
}
