package com.nmt.freelancermarketplacespringboot.repositories.posts.major;

import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorAttributeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMajorAttributeRepository extends CrudRepository<MajorAttributeEntity, Integer> {
}
