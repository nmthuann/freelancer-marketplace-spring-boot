package com.nmt.freelancermarketplacespringboot.controllers.orders;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.ModuleException;
import com.nmt.freelancermarketplacespringboot.dto.orders.CreateOrderDto;
import com.nmt.freelancermarketplacespringboot.dto.orders.CreateTransactionDto;
import com.nmt.freelancermarketplacespringboot.entities.orders.OrderEntity;
import com.nmt.freelancermarketplacespringboot.services.orders.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1.  Create Order
 * 2. getOrdersByUser (user just see themselves)
 * 3. getOrders (ADMIN)
 * 4. createReason -> Cancel Order
 * 5. createRevision -> preview product
 *  => orderCreated (status: Pending) => Seller (status: Confirmed)
 *  => Buyer transactionCreated (status: InProgress) => Seller (status: Completed)
 *  => Buyer reviewCreated
 *  //
 *  1. orderCreated (status: Pending) => Seller (status: No Reply ...) or seller createReason
 *  => order (status: Canceled)
 *  2. orderCreated (status: Pending) => Seller (status: Confirmed)
 *  => Buyer not createTransaction (status: Canceled)
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final IOrderService orderService;

    public OrderController(IOrderService orderService){
        this.orderService = orderService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createOne (
            @Valid @RequestBody CreateOrderDto data,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws ModuleException {
        OrderEntity createOrder = this.orderService.createOne(userDetails.getUsername(), data);
        return new ResponseEntity<>(createOrder, HttpStatus.OK);
    }


    @PostMapping("/create-transaction")
    public ResponseEntity<?> createTransaction (
            @Valid @RequestBody CreateTransactionDto data,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws ModuleException {
        OrderEntity createOrder = this.orderService.createTransaction(userDetails.getUsername(), data);
        return new ResponseEntity<>(createOrder, HttpStatus.OK);
    }

}
