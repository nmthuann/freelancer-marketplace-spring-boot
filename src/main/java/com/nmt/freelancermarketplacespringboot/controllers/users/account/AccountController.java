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

    private final IAccountService accountService;

    @Autowired
    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

//
//    @GetMapping("/{email}")
//    public ResponseEntity<?> getAccountById(@PathVariable String email) {
//        try {
//            AccountEntity findAcc = accountService.getOneById(email);
//
//            if (findAcc != null) {
//                return ResponseEntity.ok(findAcc);
//            } else {
//
//                return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .body(new RestResponse(
//                                false,
//                                "Account not found for email: " + email,
//                                ""));
//            }
//        } catch (Exception exception) {
//
//            String errorMessage = "Error retrieving account for email: " + email;
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body(new RestResponse(false,errorMessage, ""));
//        }
//    }
//
//
//    @PostMapping("/create")
//    public AccountEntity createAccount(@RequestBody AccountEntity data){
//        AccountEntity createAccount = accountService.createOne(data);
//        return createAccount;
//    }
//
//    @PutMapping("/update/{email}")
//    public ResponseEntity<?> updateAccount(
//            @RequestParam("email") Long accountId,
//            @RequestBody AccountEntity requestBody
//    ) {
//        try {
//            // Thực hiện logic cập nhật tài khoản với accountId và requestBody
//
//            // Nếu thành công, trả về response với HTTP status 200 (OK)
//            return ResponseEntity.ok("");
//        } catch (Exception exception) {
//            return null;
//            // Nếu có lỗi, trả về response với HTTP status 500 (Internal Server Error) và thông điệp lỗi
//            // return ResponseEntity.status(500).body(new RestResponse("Error updating account: " + httpexceptions.getMessage()));
//        }
//    }

}
