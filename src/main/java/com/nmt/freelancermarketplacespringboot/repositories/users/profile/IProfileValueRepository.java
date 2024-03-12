package com.nmt.freelancermarketplacespringboot.repositories.users.profile;

import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProfileValueRepository extends JpaRepository<ProfileValueEntity, Integer> {
    List<ProfileValueEntity> findByProfileId(Integer profileId);

}
