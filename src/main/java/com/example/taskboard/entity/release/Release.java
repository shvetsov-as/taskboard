package com.example.taskboard.entity.release;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "release")
public class Release {

    @Id
    @Column(name = "rel_id", nullable = false)
    @NotNull
    private UUID relId;

    @Column(name = "rel_version", nullable = false)
    @NotNull
    private Integer relVersion;

    @Column(name = "rel_date_from", nullable = false)
    @NotNull
    private Date relDateFrom;

    @Column(name = "rel_date_to", nullable = false)
    @NotNull
    private Date relDateTo;

    public Release() {
    }

    public Release(Integer relVersion, Date relDateFrom, Date relDateTo) {
        this.relVersion = relVersion;
        this.relDateFrom = relDateFrom;
        this.relDateTo = relDateTo;
    }

    public UUID getRelId() {
        return relId;
    }

    public void setRelId(UUID relId) {
        this.relId = relId;
    }

    public Integer getRelVersion() {
        return relVersion;
    }

    public void setRelVersion(Integer relVersion) {
        this.relVersion = relVersion;
    }

    public Date getRelDateFrom() {
        return relDateFrom;
    }

    public void setRelDateFrom(Date relDateFrom) {
        this.relDateFrom = relDateFrom;
    }

    public Date getRelDateTo() {
        return relDateTo;
    }

    public void setRelDateTo(Date relDateTo) {
        this.relDateTo = relDateTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Release)) return false;

        Release release = (Release) o;

        return relId.equals(release.relId);
    }

    @Override
    public int hashCode() {
        return relId.hashCode();
    }

    @Override
    public String toString() {
        return "Release{" +
                "relId=" + relId +
                ", relVersion=" + relVersion +
                ", relDateFrom=" + relDateFrom +
                ", relDateTo=" + relDateTo +
                '}';
    }
}
