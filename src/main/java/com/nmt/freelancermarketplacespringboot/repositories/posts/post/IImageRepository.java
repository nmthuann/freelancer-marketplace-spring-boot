package com.nmt.freelancermarketplacespringboot.repositories.posts.post;

import com.nmt.freelancermarketplacespringboot.entities.posts.post.ImageEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IImageRepository extends CrudRepository<ImageEntity, UUID> {

    List<ImageEntity> findByPost(PostEntity post);
    boolean findByUrl(String url);

}
