package com.nmt.freelancermarketplacespringboot.services.posts.category;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.CategoryException;
import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.category.CreateCategoryDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;

import java.util.List;
import java.util.Set;

public interface ICategoryService extends IBaseService<CategoryEntity, Integer> {

    CategoryEntity createOne(CreateCategoryDto data) throws CategoryException;
    CategoryEntity initCategory();

    List<CategoryEntity> getCategoriesByParentId(Integer parentId);

}
