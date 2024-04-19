package com.nmt.freelancermarketplacespringboot.repositories.orders;

import com.nmt.freelancermarketplacespringboot.entities.orders.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOrderRepository  extends JpaRepository<OrderEntity, UUID> {
}
