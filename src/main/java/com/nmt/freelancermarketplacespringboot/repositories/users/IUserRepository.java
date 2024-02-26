package com.nmt.freelancermarketplacespringboot.repositories.users;

import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
}
