package com.nmt.freelancermarketplacespringboot.core.bases;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Setter
@Getter
@MappedSuperclass
public abstract class AbstractBaseEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    // @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")

    private Date deletedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    @PreRemove
    protected void onDelete() {
        this.deletedAt = new Date();
    }
    // Getters and setters

}
