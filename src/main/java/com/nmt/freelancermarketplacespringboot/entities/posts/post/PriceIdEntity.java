package com.nmt.freelancermarketplacespringboot.entities.posts.post;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
@Embeddable
public class PriceIdEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    private int packageId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date beginAt;
}





