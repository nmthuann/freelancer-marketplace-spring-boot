package com.nmt.freelancermarketplacespringboot.modules.account;

import com.nmt.freelancermarketplacespringboot.modules.account.entities.AccountEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller // This means that this class is a Controller
@RequestMapping(path="/account")
public class AccountController {

    private  AccountRepository accountRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<AccountEntity> getAllUsers() {
        // This returns a JSON or XML with the users
        return accountRepository.findAll();
    }
}
