package com.nmt.freelancermarketplacespringboot.repositories.users.profile;


import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileAttributeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileAttributeRepository extends CrudRepository<ProfileAttributeEntity, Integer> {
}
