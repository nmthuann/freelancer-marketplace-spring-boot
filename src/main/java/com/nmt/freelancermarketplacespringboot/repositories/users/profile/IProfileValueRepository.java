package com.nmt.freelancermarketplacespringboot.repositories.users.profile;

import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileValueEntity;
import org.springframework.data.repository.CrudRepository;

public interface IProfileValueRepository extends CrudRepository<ProfileValueEntity, Integer> {
}
