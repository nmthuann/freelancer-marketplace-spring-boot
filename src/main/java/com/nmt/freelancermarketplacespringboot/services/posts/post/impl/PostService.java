package com.nmt.freelancermarketplacespringboot.services.posts.post.impl;

import com.nmt.freelancermarketplacespringboot.common.enums.PostStatusEnum;
import com.nmt.freelancermarketplacespringboot.common.exceptions.errors.ModuleException;
import com.nmt.freelancermarketplacespringboot.common.exceptions.messages.posts.PostExceptionMessages;
import com.nmt.freelancermarketplacespringboot.core.bases.AbstractBaseService;
import com.nmt.freelancermarketplacespringboot.dto.posts.post.*;
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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    @Transactional
    public PostDto createOne(String email, CreatePostDto data) throws ModuleException {

        try {
            MajorEntity findMajor = this.majorService.getOneById(data.majorId());
            if (findMajor == null){
               throw new ModuleException(PostExceptionMessages.MAJOR_INVALID.getMessage());
            }
            UserEntity findUser = this.userService.getUserByEmail(email);
            if (findUser.getProfile() == null) {
                throw new ModuleException(PostExceptionMessages.USER_NOT_PROFILE.getMessage());
            }

            PostEntity newPost = new PostEntity();
            newPost.setTitle(data.title());
            newPost.setDescription(data.description());
            newPost.setStatus(PostStatusEnum.POST_ACTIVE.getStatus());
            newPost.setFAQ(data.faq());
            newPost.setUser(findUser);
            newPost.setMajor(findMajor);

            PostEntity postCreated =
                    this.postRepository.save(newPost);

            List<ImageEntity> imagesCreated =
                    this.imageService.insertImages(postCreated, data.images()); // transaction

            List<PackageEntity> packagesCreated =
                    this.packageService.createPackageByPost(postCreated, data.packages()); // transaction


//            List<ImageDto> imageDtos = imagesCreated.stream()
//                    .map(imageEntity -> new ImageDto(imageEntity.getUrl()))
//                    .toList();


            return new PostDto (
                    postCreated.getMajor().getMajorId(),
                    postCreated.getPostId(),
                    postCreated.getTitle(),
                    postCreated.getDescription(),
                    postCreated.getFAQ(),
                    findUser.getAccount().getEmail(),
                    findUser.getLastName() +" "+findUser.getFirstName(),
                    imagesCreated,
                    packagesCreated
            );
        } catch (ModuleException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * update: update Post Information + Upload link images.
     * @param postId
     * @param data
     * @return
     * @throws ModuleException
     */
    @Override
    @Transactional
    public PostEntity updateOneById(
            String email,
            UUID postId,
            UpdatePostDto data
    ) throws ModuleException {

        PostEntity findPost = this.getOneById(postId);

        if(this.userService.getUserByEmail(email).equals(findPost.getUser())){
            throw new ModuleException(PostExceptionMessages.NOT_THE_OWNER_POST.getMessage());
        }


        findPost.setTitle(data.title());
        findPost.setFAQ(data.faq());
        findPost.setDescription(data.description());
        findPost.setStatus(data.status());

        PostEntity postUpdated
                = this.postRepository.save(findPost);
        if (data.images() != null) {
            this.imageService.updateImages(postUpdated, data.images());
        }
        return postUpdated;
    }


    @Override
    public List<PostEntity> getPostsByUserId(String email) {
        UserEntity findUser = this.userService.getUserByEmail(email);
        return this.postRepository.findByUser(findUser);
    }


    @Override
    public Page<PostEntity> getPostsByMajorId(int majorId, int size, int page) {
        MajorEntity findMajor = this.majorService.getOneById(majorId);
        Pageable pageable = PageRequest.of(page - 1, size - 1);
        return this.postRepository.findByMajor(findMajor, pageable);
    }


    @Override
    public List<PostEntity> getPostsByCategoryId(int categoryId) {

        int size = 10;
        int page = 1;
        List<PostEntity> posts = new ArrayList<>();

        List<MajorEntity> findMajors = this.majorService.getMajorsByCategory(categoryId);

        for (MajorEntity major: findMajors){
            Pageable pageable = PageRequest.of(page - 1, size - 1);

            Page<PostEntity> getPostsByMajorId =  this.postRepository.findByMajor(major, pageable);
            posts.addAll((Collection<? extends PostEntity>) getPostsByMajorId);
        }

        return posts;
    }





    @Override
    public void softDelete(UUID id) {
        Date now = new Date();
        Optional<PostEntity> findById = this.postRepository.findById(id);
        findById.ifPresent(post -> {
            post.setDeletedAt(now);
            this.postRepository.save(post);
        });
    }


    @Override
    @Transactional
    public PackageEntity updatePackageByPost (
            UUID postId,
            int packageId,
            UpdatePackageDto data
    ) throws ModuleException {
        PackageEntity findPackage  = this.packageService.getOneById(packageId);
        if (findPackage.getPost().getPostId() != postId) {
            throw new ModuleException(PostExceptionMessages.PACKAGE_NOT_FOUND.getMessage());
        }
        return this.packageService.updateOneById(findPackage, data);
    }

    @Override
    public Page<PostEntity> getAllPosts(
            int size,
            int page,
            int majorId,
            Boolean latest,
            Boolean bestSeller,
            Boolean topFeedback
    ) {
        Page<PostEntity> postsPage;

        // Pagination logic
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdAt").descending());
        if (latest != null && latest) {
            postsPage = postRepository.findAll(pageRequest);
        } else if (majorId != 0) {
            MajorEntity major = this.majorService.getOneById(majorId);
            if (major != null) {
                postsPage = postRepository.findByMajor(major, pageRequest);

            } else {
                postsPage = Page.empty(); // No posts found for the provided majorId
            }
        } else if (bestSeller != null && bestSeller) {
            // Logic to retrieve best-selling posts
            // postsPage = postRepository.findByBestSellerTrue(pageRequest);
            System.out.println("bestSeller" + bestSeller);
            postsPage = Page.empty();
        } else if (topFeedback != null && topFeedback) {
            // Logic to retrieve posts with top feedback
            // postsPage = postRepository.findByTopFeedbackTrue(pageRequest);
            System.out.println("topFeedback" + topFeedback);
            postsPage = Page.empty();
        } else {
            // Fetch all posts if no specific criteria are provided
            postsPage = postRepository.findAll(pageRequest);
        }

        return postsPage;

    }

    @Override
    public PostDto getPostByPackages(UUID postId) {
        PostEntity findPost = this.getOneById(postId);
        return new PostDto (
                findPost.getMajor().getMajorId(),
                findPost.getPostId(),
                findPost.getTitle(),
                findPost.getDescription(),
                findPost.getFAQ(),
                findPost.getUser().getAccount().getEmail(),
                findPost.getUser().getFirstName() + " " + findPost.getUser().getFirstName(),
                findPost.getImages(),
                this.packageService.getPackagesByPostId(findPost)
        );
    }

    @Override
    public List<GetPostDto> getAllPost() {
        return null;
    }

    @Override
    public GetPostDto getPostById(UUID postId) {
        PostEntity findById = this.getOneById(postId);

        List<PackageEntity> findPackagesOfPost =  this.packageService.getPackagesByPostId(findById);
        List<PackageDto> packageDtos = new ArrayList<>();
        for (PackageEntity packageEntity: findPackagesOfPost){
            packageDtos.add(this.packageService.getPackageByPriceId(packageEntity));
        }


        return new GetPostDto(
                findById.getMajor().getMajorId(),
                findById.getPostId(),
                findById.getTitle(),
                findById.getDescription(),
                findById.getFAQ(),
                findById.getUser().getAccount().getEmail(),
                findById.getUser().getFirstName() + " " + findById.getUser().getFirstName(),
                findById.getImages(),
                packageDtos

        );
    }
//    int majorId,
//    UUID postId,
//    String title,
//    String description,
//    String faq,
//    String sellerEmail,
//    String sellerFullName,
//    List<ImageDto> images,
//    List<PackageDto>packages

}
