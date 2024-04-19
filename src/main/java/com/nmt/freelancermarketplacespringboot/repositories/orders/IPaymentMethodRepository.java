package com.nmt.freelancermarketplacespringboot.repositories.orders;

import com.nmt.freelancermarketplacespringboot.entities.orders.PaymentMethodEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentMethodRepository extends CrudRepository<PaymentMethodEntity, Integer> {
}
