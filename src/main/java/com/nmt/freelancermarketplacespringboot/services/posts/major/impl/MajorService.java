package com.nmt.freelancermarketplacespringboot.services.posts.major.impl;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.ModuleException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.posts.MajorExceptionMessages;
import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.major.CreateMajorAttributeDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.major.CreateMajorDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.major.CreateMajorEavDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.major.CreateMajorValueDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.category.CategoryEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorValueEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.major.IMajorRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.category.ICategoryService;
import com.nmt.freelancermarketplacespringboot.services.posts.major.IMajorAttributeService;
import com.nmt.freelancermarketplacespringboot.services.posts.major.IMajorService;
import com.nmt.freelancermarketplacespringboot.services.posts.major.IMajorValueService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MajorService
        extends AbstractBaseService<MajorEntity, Integer>
            implements IMajorService {


    @Autowired
    IMajorRepository majorRepository;

    @Autowired
    ICategoryService categoryService;

    @Autowired
    IMajorAttributeService majorAttributeService;

    @Autowired
    IMajorValueService majorValueService;

    @Autowired
    public MajorService(IMajorRepository majorRepository) {
        super(majorRepository);
    }


    @Override
    public List<MajorEntity> getMajorsByCategory(int categoryId) {
        CategoryEntity findCategory = this.categoryService.getOneById(categoryId);
        return majorRepository.findByCategory(findCategory);
    }

    @Override
    @Transactional
    public MajorEntity createMajorEav(CreateMajorEavDto data) {

        CategoryEntity findCategory = this.categoryService.getOneById(data.categoryId());

        MajorEntity newMajor = new MajorEntity();
        newMajor.setMajorName(data.majorName());
        newMajor.setCategory(findCategory);

        MajorEntity majorCreated = this.majorRepository.save(newMajor);


        // create Transaction
        Set<MajorValueEntity> majorValueEntities = new HashSet<>();

        for (CreateMajorAttributeDto attribute : data.majorAttributes()) {
            System.out.println(data.majorAttributes());

            for (CreateMajorValueDto value: attribute.majorValues()){

                // data.profileAttributes().iterator().next().profileValues()
                MajorValueEntity majorValueEntity = new MajorValueEntity();

                majorValueEntity.setMajor(majorCreated);

                majorValueEntity.setMajorAttribute(
                        this.majorAttributeService.getOneById(attribute.majorAttributeId()));

                majorValueEntity.setValue(value.majorValue());

                MajorValueEntity majorValueCreated = this.majorValueService.createOne(majorValueEntity);

                majorValueEntities.add(majorValueCreated);
            }

            this.majorValueService.saveAll(majorValueEntities);

        }
        // this.profileValueService.saveAll(profileValueEntities);
        return majorCreated;
    }


    @Override
    @Transactional
    public MajorEntity createOne(CreateMajorDto data) throws ModuleException{
        CategoryEntity findCategory = this.categoryService.getOneById(data.categoryId());
        if(findCategory == null){
            throw new ModuleException(MajorExceptionMessages.CATEGORY_NOT_FOUND.getMessage());
        }
        MajorEntity newMajor = new MajorEntity();
        newMajor.setMajorName(data.majorName());
        newMajor.setCategory(findCategory);
        return this.majorRepository.save(newMajor);
    }


}
