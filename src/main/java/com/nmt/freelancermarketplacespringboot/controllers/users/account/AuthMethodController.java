package com.nmt.freelancermarketplacespringboot.controllers.users.account;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseController;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AuthMethodEntity;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAuthMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authMethods")
public class AuthMethodController extends AbstractBaseController<AuthMethodEntity, Integer> {

    @Autowired
    IAuthMethodService authMethodService;

    public  AuthMethodController(IAuthMethodService authMethodService) {
        super(authMethodService);
        this.authMethodService = authMethodService;
    }

//    @Autowired
//    public AuthMethodController(IAuthMethodService authMethodService) {
//        super(authMethodService);
//    }
}
