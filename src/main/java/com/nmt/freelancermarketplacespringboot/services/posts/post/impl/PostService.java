package com.nmt.freelancermarketplacespringboot.services.posts.post.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.post.IPostRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class PostService extends AbstractBaseService<PostEntity, UUID> implements IPostService {
    @Autowired
    IPostRepository postRepository;

    @Autowired
    public PostService (IPostRepository postRepository){
        super(postRepository);
    }
}
