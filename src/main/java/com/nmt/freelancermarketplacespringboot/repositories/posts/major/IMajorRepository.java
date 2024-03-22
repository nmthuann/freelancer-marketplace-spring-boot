package com.nmt.freelancermarketplacespringboot.repositories.posts.major;

import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMajorRepository extends CrudRepository<MajorEntity, Integer> {
}
