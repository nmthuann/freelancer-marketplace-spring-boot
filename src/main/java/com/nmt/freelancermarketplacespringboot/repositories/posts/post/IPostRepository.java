package com.nmt.freelancermarketplacespringboot.repositories.posts.post;

import com.nmt.freelancermarketplacespringboot.entities.posts.major.MajorEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IPostRepository extends JpaRepository<PostEntity, UUID> {
    List<PostEntity> findByUser(UserEntity user);
    Page<PostEntity> findByMajor(MajorEntity major, Pageable pageable);
//    Page<PostEntity> findTopNByOrderByCreatedAtDesc(MajorEntity major);
//    @Query("SELECT p FROM PostEntity o JOIN o.packageEntity p JOIN p.post post WHERE post.bestSeller = true")
//    List<PostEntity> findByBestSellerPosts();
}
