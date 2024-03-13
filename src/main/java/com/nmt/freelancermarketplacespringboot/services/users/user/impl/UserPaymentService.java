package com.nmt.freelancermarketplacespringboot.services.users.user.impl;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.AuthExceptionMessage;
import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserPaymentEntity;
import com.nmt.freelancermarketplacespringboot.repositories.users.user.IUserPaymentRepository;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class UserPaymentService
        extends AbstractBaseService<UserPaymentEntity, UUID>
        implements IUserPaymentService {

//    @Autowired
//    IAccountService accountService;

    @Autowired
    public UserPaymentService(IUserPaymentRepository userPaymentRepository) {
        super(userPaymentRepository);
    }

//    @Override
//    public UserPaymentEntity createOne(UserPaymentEntity data, String email) throws AuthException {
//        AccountEntity account = this.accountService.getOneById(email);
//        if( account == null || !account.isStatus()){
//            throw new AuthException(AuthExceptionMessage.EMAIL_INVALID.getMessage());
//        }
//        return super.createOne(data);
//    }

    public UserPaymentEntity getUserPaymentByEmail(String email){
        return null;
    }
}
