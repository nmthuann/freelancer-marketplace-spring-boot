package com.nmt.freelancermarketplacespringboot.controllers.users.account;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseController;
import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.entities.users.account.RoleEntity;
import com.nmt.freelancermarketplacespringboot.services.users.account.IRoleService;
import com.nmt.freelancermarketplacespringboot.services.users.account.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController extends AbstractBaseController<RoleEntity, Integer> {
    
    @Autowired
    private final IRoleService roleService;


    public RoleController(IRoleService roleService) {
        super(roleService);
        this.roleService = roleService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOneById(@PathVariable Integer id) {
        roleService.softDeleteRole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
