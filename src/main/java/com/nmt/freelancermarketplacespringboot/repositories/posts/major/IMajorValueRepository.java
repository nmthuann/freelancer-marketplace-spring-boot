package com.nmt.freelancermarketplacespringboot.repositories.posts.major;

import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMajorValueRepository extends JpaRepository<MajorValueEntity, Integer> {
}
