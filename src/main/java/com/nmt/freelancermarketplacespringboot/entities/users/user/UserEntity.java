package com.nmt.freelancermarketplacespringboot.entities.users.user;


// import com.nmt.freelancermarketplacespringboot.entities.posts.post.PostEntity;
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

    @Column(name = "first_name", columnDefinition="NVARCHAR(50)" ,nullable = false)
    // @Nationalized
    private String firstName;

    @Column(name = "last_name",  columnDefinition="NVARCHAR(255)")
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
    @JoinColumn(name = "email", referencedColumnName = "email", unique=true)
    // Use referencedColumnName to specify the referenced column
    private AccountEntity account;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_payment_id")
    private UserPaymentEntity userPayment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;


// //

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ReviewEntity review;

}
