package com.nmt.freelancermarketplacespringboot.repositories.users.profile;


import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileAttributeEntity;
import org.springframework.data.repository.CrudRepository;

public interface IProfileAttributeRepository extends CrudRepository<ProfileAttributeEntity, Integer> {
}
