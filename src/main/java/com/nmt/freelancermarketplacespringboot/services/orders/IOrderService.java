package com.nmt.freelancermarketplacespringboot.services.orders;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.entities.orders.OrderEntity;

import java.util.UUID;

public interface IOrderService extends IBaseService<OrderEntity, UUID> {
}
