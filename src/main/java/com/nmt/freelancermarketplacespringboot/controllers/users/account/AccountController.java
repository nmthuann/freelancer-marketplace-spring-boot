package com.nmt.freelancermarketplacespringboot.controllers.users.account;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseController;
import com.nmt.freelancermarketplacespringboot.dto.users.account.CreateAccountDto;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/accounts")
public class AccountController   { //extends AbstractBaseController<AccountEntity, String>

    @Autowired
    IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
       this.accountService = accountService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<AccountEntity> getOneById(@PathVariable String email) {
//        AccountEntity findEntity = this.accountService.getOneById(email);
//        if (findEntity != null) {
//            return ResponseEntity.ok(findEntity);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
        Optional<AccountEntity> optionalAccount = Optional.ofNullable(this.accountService.getOneById(email));

        // Sử dụng ifPresent để kiểm tra xem giá trị có tồn tại không
//        if (optionalAccount.isPresent()) {
//            return ResponseEntity.ok(optionalAccount.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
        return optionalAccount.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<AccountEntity> createOne(@RequestBody CreateAccountDto data) {
        AccountEntity created =  this.accountService.createOne(data);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }



}



