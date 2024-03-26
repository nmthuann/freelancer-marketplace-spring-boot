package com.nmt.freelancermarketplacespringboot.services.posts.post.impl;

import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.CreatePostDto;
import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.ImageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PackageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import com.nmt.freelancermarketplacespringboot.repositories.posts.post.IPostRepository;
import com.nmt.freelancermarketplacespringboot.services.posts.major.IMajorService;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPackageService;
import com.nmt.freelancermarketplacespringboot.services.posts.post.IPostService;
import com.nmt.freelancermarketplacespringboot.services.users.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService extends AbstractBaseService<PostEntity, UUID> implements IPostService {
    @Autowired
    IPostRepository postRepository;

    @Autowired
    IMajorService majorService;

    @Autowired
    IUserService userService;

    @Autowired
    ImageService imageService;

    @Autowired
    IPackageService packageService;

    @Autowired
    public PostService (IPostRepository postRepository){
        super(postRepository);
    }

    /**
     * 1. create Post
     * 2. create Image
     * 3. create Package
     * 4. create Price
     * => complete create Post
     * @param email
     * @param data
     * @return
     * => 1 method have 3 API or 1API call 3 method.
     */
    @Override
    public PostEntity createOne(String email, CreatePostDto data) {

        MajorEntity findMajor = this.majorService.getOneById(data.majorId());

        UserEntity findUser = this.userService.getUserByEmail(email);

        PostEntity newPost = new PostEntity();
        newPost.setTitle(data.title());
        newPost.setDescription(data.description());
        newPost.setFAQ(data.faq());
        newPost.setUser(findUser);
        newPost.setMajor(findMajor);

         PostEntity postCreated =
                 this.postRepository.save(newPost);

         List<ImageEntity> imagesCreated =
                 this.imageService.insertImages(postCreated, data.images()); // transaction


        List<PackageEntity> packagesCreated =
                this.packageService.createPackageByPost(postCreated, data.packages()); // transaction



        return postCreated;
    }
}
