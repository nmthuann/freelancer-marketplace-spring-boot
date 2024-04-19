package com.nmt.freelancermarketplacespringboot.services.orders;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.entities.orders.TransactionEntity;

import java.util.UUID;

public interface ITransactionService extends IBaseService<TransactionEntity, UUID> {
}
