package com.nmt.freelancermarketplacespringboot.services.users.account;

import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.account.RoleEntity;

public interface IRoleService extends IBaseService<RoleEntity, Integer> {
    void softDeleteRole(Integer id);
}
