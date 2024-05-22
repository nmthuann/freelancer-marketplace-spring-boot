package com.nmt.freelancermarketplacespringboot.services.orders;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.ModuleException;
import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.orders.CreateOrderDto;
import com.nmt.freelancermarketplacespringboot.dto.orders.CreateTransactionDto;
import com.nmt.freelancermarketplacespringboot.dto.orders.UpdateOrderDto;
import com.nmt.freelancermarketplacespringboot.entities.orders.OrderEntity;

import java.util.UUID;

public interface IOrderService extends IBaseService<OrderEntity, UUID> {
    OrderEntity createOne(String email, CreateOrderDto data);
    OrderEntity updateStatus(UUID orderId, UpdateOrderDto data);
    OrderEntity createTransaction(String email, CreateTransactionDto data) throws ModuleException;
}
