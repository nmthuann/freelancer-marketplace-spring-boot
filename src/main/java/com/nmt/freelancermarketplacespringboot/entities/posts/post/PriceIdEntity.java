package com.nmt.freelancermarketplacespringboot.entities.posts.post;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@Data
@Embeddable
public class PriceIdEntity implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;

//    private int packageId;
    @ManyToOne(fetch = FetchType.EAGER)
    // @MapsId("packageId")
    @JoinColumn(name = "package_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_price_package"))
    private PackageEntity packageEntity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="begin_at")
    private Date beginAt;
}


//@ManyToOne(fetch = FetchType.EAGER)
//@MapsId("packageId")
//@JoinColumn(name = "package_id", nullable = false,
//        foreignKey = @ForeignKey(name = "fk_price_package"))
//private PackageEntity packageEntity;



//@Embeddable
//public class RecordId implements Serializable{
//    /**
//     *
//     */
//    private static final long serialVersionUID = 8167040004250354298L;
//
//    @Column(name="Record_DOMAIN", nullable = false)
//    @NotNull(message = "Record Domain cannot be empty")
//    @JsonProperty("RecordDomain")
//    private String recordDomain;
//
//    @Column(name="ENVIRONMENT_TYPE", nullable = false)
//    @NotNull(message = "Environment Type cannot be empty")
//    @Enumerated(EnumType.STRING)
//    @JsonProperty("environmentType")
//    private EnvironmentTypes environmentType;
//
//    @Column(name="UPGRADE", nullable = false)
//    @JsonProperty("upgrade")
//    private boolean upgrade;
//
//    @Column(name="VERSION", nullable = false)
//    @JsonProperty("version")
//    private String version = "2.0";
//
//    @ApiModelProperty(value = "Enironment type for which the Record Domain is
//            created.", required = true )
//            public EnvironmentTypes getEnvironmentType() {
//            return environmentType;
//            }
//
//            public void setEnvironmentType(EnvironmentTypes environmentType) {
//        this.environmentType = environmentType;
//    }
//
//    @ApiModelProperty(value = "Cusotmer Domain name", required = true )
//    public String getRecordDomain() {
//        return recordDomain;
//    }
//
//    public void setRecordDomain(String recordDomain) {
//        this.recordDomain = recordDomain;
//    }
//
//
//    public RecordId(){
//    }
//
//    public RecordId(String recordDomain, EnvironmentTypes environmentType){
//        this.recordDomain = recordDomain;
//        this.environmentType = environmentType;
//    }
//
//    public RecordId(String recordDomain, EnvironmentTypes environmentType,
//                    boolean upgrade, String version){
//        this.recordDomain = recordDomain;
//        this.environmentType = environmentType;
//        this.upgrade = upgrade;
//        this.version = version;
//    }
//
//    public boolean isUpgrade() {
//        return upgrade;
//    }
//
//    public void setUpgrade(boolean upgrade) {
//        this.upgrade = upgrade;
//    }
//
//    public String getVersion() {
//        return version;
//    }
//
//    public void setVersion(String version) {
//        if( version != null) {
//            this.version = version;
//        }else {
//            this.version = "18.2";
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof RecordId)) return false;
//        RecordId that = (RecordId) o;
//        return Objects.equals(getRecordDomain(), that.getRecordDomain()) &&
//                Objects.equals(getEnvironmentType(),
//                        that.getEnvironmentType());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getRecordDomain(), getEnvironmentType());
//    }
//
//}

