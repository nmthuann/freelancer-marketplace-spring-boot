package com.nmt.freelancermarketplacespringboot.services.orders.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.orders.TransactionEntity;
import com.nmt.freelancermarketplacespringboot.repositories.orders.ITransactionRepository;
import com.nmt.freelancermarketplacespringboot.services.orders.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService
        extends AbstractBaseService<TransactionEntity, UUID>
        implements ITransactionService {
    private final ITransactionRepository transactionRepository;

    @Autowired
    public TransactionService(ITransactionRepository transactionRepository) {
        super(transactionRepository);
        this.transactionRepository = transactionRepository;
    }
}
