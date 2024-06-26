package com.nmt.freelancermarketplacespringboot.services.posts.category;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.ModuleException;
import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.category.CreateCategoryDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;

import java.util.List;

public interface ICategoryService extends IBaseService<CategoryEntity, Integer> {

    CategoryEntity createOne(CreateCategoryDto data) throws ModuleException;
    CategoryEntity initCategory();

    List<CategoryEntity> getCategoriesByParentId(Integer parentId);

}
