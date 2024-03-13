package com.nmt.freelancermarketplacespringboot.services.users.user;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;
import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserPaymentEntity;

import java.util.UUID;

public interface IUserPaymentService extends IBaseService<UserPaymentEntity, UUID> {
    // UserPaymentEntity createOne(UserPaymentEntity data, String email) throws AuthException;
}
