package com.example.taskboard.entity.release.dto;

import com.sun.istack.NotNull;

import java.util.Date;

public class ReleaseDtoRequest {

    private Long relId;
    @NotNull
    private Integer relVersion;
    @NotNull
    private Date relDateFrom;
    @NotNull
    private Date relDateTo;

    public ReleaseDtoRequest() {
    }

    public ReleaseDtoRequest(Integer relVersion, Date relDateFrom, Date relDateTo) {
        this.relVersion = relVersion;
        this.relDateFrom = relDateFrom;
        this.relDateTo = relDateTo;
    }

    public Long getRelId() {
        return relId;
    }

    public void setRelId(Long relId) {
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
        if (!(o instanceof ReleaseDtoRequest)) return false;

        ReleaseDtoRequest that = (ReleaseDtoRequest) o;

        if (!getRelVersion().equals(that.getRelVersion())) return false;
        if (!getRelDateFrom().equals(that.getRelDateFrom())) return false;
        return getRelDateTo().equals(that.getRelDateTo());
    }

    @Override
    public int hashCode() {
        int result = getRelVersion().hashCode();
        result = 31 * result + getRelDateFrom().hashCode();
        result = 31 * result + getRelDateTo().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ReleaseDtoRequest{" +
                "relVersion=" + relVersion +
                ", relDateFrom=" + relDateFrom +
                ", relDateTo=" + relDateTo +
                '}';
    }
}
