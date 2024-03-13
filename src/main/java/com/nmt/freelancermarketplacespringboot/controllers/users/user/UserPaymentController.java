package com.nmt.freelancermarketplacespringboot.controllers.users.user;


import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.AuthException;
import com.nmt.freelancermarketplacespringboot.dto.users.user.CreateUserPaymentDto;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserPaymentEntity;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserPaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userPayments")
public class UserPaymentController {

    @Autowired
    IUserPaymentService userPaymentService;



    @PostMapping("/create")
    public ResponseEntity<?> createUserPayment (
            @Valid @RequestBody CreateUserPaymentDto data,
            @NonNull HttpServletRequest request
            ) throws AuthException {
        System.out.println("CREATE USER PAYMENT.....");

        String email = (String) request.getAttribute("email");

        UserPaymentEntity createUserPayment = new UserPaymentEntity();
        createUserPayment.setCardNumber(data.cardNumber());
        createUserPayment.setCardholderName(data.cardholderName());
        createUserPayment.setCvv(data.cvv());
        createUserPayment.setExpired(data.expired());
        createUserPayment.setCountry(data.country());


        // must be seller
        UserPaymentEntity created = this.userPaymentService.createOne(createUserPayment);

        return new ResponseEntity<>(created, HttpStatus.OK);
    }


}
