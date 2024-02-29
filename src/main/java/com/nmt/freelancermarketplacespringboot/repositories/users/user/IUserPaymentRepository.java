package com.nmt.freelancermarketplacespringboot.repositories.users.user;

import com.nmt.freelancermarketplacespringboot.entities.users.user.UserPaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface IUserPaymentRepository extends CrudRepository<UserPaymentEntity, UUID> {
}
