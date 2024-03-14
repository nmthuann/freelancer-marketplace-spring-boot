package com.nmt.freelancermarketplacespringboot.repositories.users.user;

import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    // @Query("select u from users u where u.email = ?1")
    UserEntity findByAccountEmail(String email);
    Page<UserEntity> findByProfileIsNotNull(Pageable pageable);
}
