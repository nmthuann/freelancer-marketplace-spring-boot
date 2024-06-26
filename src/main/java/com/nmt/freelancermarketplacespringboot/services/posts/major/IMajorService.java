package com.nmt.freelancermarketplacespringboot.services.posts.major;

import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.ModuleException;
import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.major.CreateMajorDto;
import com.nmt.freelancermarketplacespringboot.dto.posts.major.CreateMajorEavDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;

import java.util.List;

public interface IMajorService extends IBaseService<MajorEntity, Integer> {
    List<MajorEntity> getMajorsByCategory(int categoryId);
    MajorEntity createMajorEav(CreateMajorEavDto data);
    MajorEntity createOne(CreateMajorDto data) throws ModuleException;
}
