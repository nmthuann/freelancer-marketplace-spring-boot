package com.nmt.freelancermarketplacespringboot.repositories.posts.category;

import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends CrudRepository<CategoryEntity, Integer> {
}
