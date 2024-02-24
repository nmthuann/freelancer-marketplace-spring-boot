package com.nmt.freelancermarketplacespringboot.entities.users.user;


// import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
import com.nmt.freelancermarketplacespringboot.entities.orders.OrderEntity;
import com.nmt.freelancermarketplacespringboot.entities.orders.RevisionEntity;
import com.nmt.freelancermarketplacespringboot.entities.posts.review.ReviewEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.account.AccountEntity;
import com.nmt.freelancermarketplacespringboot.entities.users.profile.ProfileEntity;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Data
@Entity
@Table(name = "Users", uniqueConstraints={@UniqueConstraint(columnNames ={"email"})}) //, uniqueConstraints = @UniqueConstraint(columnNames = "email")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id", updatable = false, nullable = false)
    private UUID userId;

    @Column(name = "first_name", length = 50, nullable = false)
    @Nationalized
    private String firstName;

    @Column(name = "last_name")
    @Nationalized
    private String lastName;

    @Column(name = "avatar_url")
    private String avatarURL;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @Column(name = "location")
    private String location;

    @Column(name = "phone", length = 10, nullable = false)
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "email",
            referencedColumnName = "email",
            unique=true,
            nullable = false
    )
    private AccountEntity account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_payment_id")
    private UserPaymentEntity userPayment;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "profile_id", referencedColumnName = "id",unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "user_profile",
            joinColumns =
                    { @JoinColumn(name = "user_id", referencedColumnName = "user_id") },
            inverseJoinColumns =
                    { @JoinColumn(name = "profile_id", referencedColumnName = "profile_id") })
    // purpose: using a join table in this case can help us to eliminate these null values
    private ProfileEntity profile;


    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ReviewEntity review;


    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private Set<OrderEntity> sellerOrders;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private Set<OrderEntity> buyerOrders;

}
