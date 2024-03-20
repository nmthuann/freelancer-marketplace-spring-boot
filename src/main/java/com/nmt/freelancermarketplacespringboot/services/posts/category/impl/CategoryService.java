package com.nmt.freelancermarketplacespringboot.services.posts.category.impl;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.CategoryException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.CategoryExceptionMessage;
import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.category.CreateCategoryDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.category.ICategoryRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.category.ICategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbstractBaseService<CategoryEntity, Integer> implements ICategoryService {

    @Autowired
    ICategoryRepository categoryRepository;

    @Autowired
    public CategoryService(ICategoryRepository categoryRepository) {
        super(categoryRepository);
    }


    /**
     *  // find categoryParentId
     *
     * @param data {categoryParentId, categoryName}
     * @return
     */
    @Override
    @Transactional
    public CategoryEntity creatOne(CreateCategoryDto data) throws CategoryException {

        CategoryEntity findParentNode = this.getOneById(data.categoryParentId());

        if (findParentNode == null){
            throw new CategoryException(CategoryExceptionMessage.CATEGORY_PARENT_NOT_FOUND.getMessage());
        }

        /*
         * Kỹ thuật: Nested Set Model
         * (1, 'Electronics', 1, 10),
         * (2, 'Mobile Phones', 2, 5),
         * (3, 'Laptops', 6, 9),
         * (4, 'Smartphones', 3, 4),
         * (5, 'Gaming Laptops', 7, 8);
         *
         * 2 update command, 1 insert command
         *
         * step 1: select information parent node.
         * step 2: update the right values by 2, including the parent node. (gte right of the parent node)
         * => right value of parent node is condition. => update parent node.
         * step 3: update the left values by 2, not including the parent node. (gt right of the parent node)
         * step 4: Add a new node with the left value being the right value of the parent node,
         *          and the right value being the right value of the parent node plus 1
         */

        categoryRepository.incrementRightValue(findParentNode.getRightValue());
        categoryRepository.incrementLeftValue(findParentNode.getRightValue());

        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setCategoryName(data.categoryName());
        newCategory.setDescription("");
        newCategory.setLeftValue(findParentNode.getRightValue());
        newCategory.setRightValue(findParentNode.getRightValue() + 1);

        return this.categoryRepository.save(newCategory);
    }

    @Override
    public CategoryEntity initCategory() {
        if (this.categoryRepository.count() == 0){
            CategoryEntity root = new CategoryEntity();
            root.setCategoryName("ROOT");
            root.setLeftValue(1);
            root.setRightValue(2);
            root.setDescription("Node Root");
            return this.createOne(root);
        }
        return null;
    }
}

