package com.nmt.freelancermarketplacespringboot.services.orders.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.orders.OrderEntity;
import com.nmt.freelancermarketplacespringboot.repositories.orders.IOrderRepository;
import com.nmt.freelancermarketplacespringboot.services.orders.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService extends AbstractBaseService<OrderEntity, UUID> implements IOrderService {

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository) {
        super(orderRepository);
    }
}
