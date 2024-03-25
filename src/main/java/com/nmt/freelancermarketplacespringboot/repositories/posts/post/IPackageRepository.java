package com.nmt.freelancermarketplacespringboot.repositories.posts.post;

import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPackageRepository extends CrudRepository<PackageEntity, Integer> {
}
