package com.example.taskboard.entity.release;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "release")
public class Release {

    @Id
    @SequenceGenerator(
            name = "release_sequence",
            sequenceName = "release_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "release_sequence"
    )
    @Column(name = "rel_id", nullable = false)
    @NotNull
    private Long relId;

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

    public Long getRelId() {
        return relId;
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
