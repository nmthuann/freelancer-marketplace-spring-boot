package com.nmt.freelancermarketplacespringboot.repositories.orders;

import com.nmt.freelancermarketplacespringboot.entities.orders.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, UUID> {
}
