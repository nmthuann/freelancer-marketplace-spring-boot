package com.nmt.freelancermarketplacespringboot.repositories.orders;

import com.nmt.freelancermarketplacespringboot.entities.orders.RevisionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRevisionRepository extends JpaRepository<RevisionEntity, Long> {
}
