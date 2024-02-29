package com.nmt.freelancermarketplacespringboot.repositories.users.profile;

import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProfileRepository extends CrudRepository<ProfileEntity, Integer> {
}
