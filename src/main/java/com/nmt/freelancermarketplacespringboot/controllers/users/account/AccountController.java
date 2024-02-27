package com.nmt.freelancermarketplacespringboot.controllers.users.account;

import com.nmt.freelancermarketplacespringboot.common.utils.RestResponse;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.services.users.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
//    @Autowired
//    AccountService accountService;

    private final IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping("/{email}")
    public ResponseEntity<RestResponse> getAccountById(@PathVariable String email) {
//        AccountEntity findAccount =
        System.out.println("Hahaha");
        // AccountEntity findAcc = accountService.getOneById(email);
        try {
            AccountEntity findAcc = accountService.getOneById(email);
            //return ResponseEntity.ok(findAcc);
            return new ResponseEntity<RestResponse>(
                    new RestResponse("Succesfully Logged In!", findAcc),HttpStatus.OK);
        }catch (Exception exception){
            String errorMessage = "Error retrieving account for email: " + email;
//            return ResponseEntity.notFound().header("error-message", errorMessage).build();
           // return ResponseEntity.status(HttpStatus.NOT_FOUND).
            return new ResponseEntity<RestResponse>(
                    new RestResponse("Succesfully Logged In!", ""),HttpStatus.NOT_FOUND);
        }
        // return null;
    }

    @PostMapping("/create")
    public AccountEntity createAccount(@RequestBody AccountEntity data){
        AccountEntity createAccount = accountService.createOne(data);
        return createAccount;
    }

//    @PutMapping("/update/{email}")
//    public ResponseEntity<RestResponse> updateAccount(
//            @RequestParam("email") Long accountId,
//            @RequestBody AccountEntity requestBody
//    ) {
//        try {
//            // Thực hiện logic cập nhật tài khoản với accountId và requestBody
//
//            // Nếu thành công, trả về response với HTTP status 200 (OK)
//            return ResponseEntity.ok(new RestResponse("Account updated successfully"));
//        } catch (Exception exception) {
//            // Nếu có lỗi, trả về response với HTTP status 500 (Internal Server Error) và thông điệp lỗi
//            return ResponseEntity.status(500).body(new RestResponse("Error updating account: " + exception.getMessage()));
//        }
//    }

}
