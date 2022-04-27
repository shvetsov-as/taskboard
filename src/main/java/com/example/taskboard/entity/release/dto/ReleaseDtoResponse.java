package com.example.taskboard.entity.release.dto;

import java.util.Date;
import java.util.UUID;

public class ReleaseDtoResponse {

    private UUID relId;
    private Integer relVersion;
    private Date relDateFrom;
    private Date relDateTo;

    public ReleaseDtoResponse() {
    }

    public ReleaseDtoResponse(UUID relId, Integer relVersion, Date relDateFrom, Date relDateTo) {
        this.relId = relId;
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
        if (!(o instanceof ReleaseDtoResponse)) return false;

        ReleaseDtoResponse that = (ReleaseDtoResponse) o;

        if (!getRelId().equals(that.getRelId())) return false;
        if (!getRelDateFrom().equals(that.getRelDateFrom())) return false;
        return getRelDateTo().equals(that.getRelDateTo());
    }

    @Override
    public int hashCode() {
        int result = getRelId().hashCode();
        result = 31 * result + getRelDateFrom().hashCode();
        result = 31 * result + getRelDateTo().hashCode();
        return result;
    }

}
