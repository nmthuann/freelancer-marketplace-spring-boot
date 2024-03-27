package com.nmt.freelancermarketplacespringboot.repositories.posts.post;

import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IPostRepository extends CrudRepository<PostEntity, UUID> {
    List<PostEntity> findByUser(UserEntity user);
    List<PostEntity> findByMajor(MajorEntity major);
}
