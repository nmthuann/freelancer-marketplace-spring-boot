package com.nmt.freelancermarketplacespringboot.repositories.orders;

import com.nmt.freelancermarketplacespringboot.entities.orders.ReasonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReasonRepository extends CrudRepository<ReasonEntity, Long> {
}
