package com.nmt.freelancermarketplacespringboot.services.orders.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.orders.PaymentMethodEntity;
import com.nmt.freelancermarketplacespringboot.repositories.orders.IPaymentMethodRepository;
import com.nmt.freelancermarketplacespringboot.services.orders.IPaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodService
        extends AbstractBaseService<PaymentMethodEntity, Integer>
        implements IPaymentMethodService {

    @Autowired
    public PaymentMethodService(IPaymentMethodRepository paymentMethodRepository) {
        super(paymentMethodRepository);
    }
}
