package com.nmt.freelancermarketplacespringboot.controllers.posts.major;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseController;
import com.nmt.freelancermarketplacespringboot.core.bases.IBaseService;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorAttributeEntity;
import com.nmt.freelancermarketplacespringboot.services.posts.major.IMajorAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/majorAttributes")
public class MajorAttributeController  extends AbstractBaseController<MajorAttributeEntity, Integer> {

    @Autowired
    public MajorAttributeController(IMajorAttributeService majorAttributeService) {
        super(majorAttributeService);
    }
}
