package com.nmt.freelancermarketplacespringboot.repositories.posts.major;

import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMajorRepository extends CrudRepository<MajorEntity, Integer> {
    List<MajorEntity> findByCategory(CategoryEntity category);
}
