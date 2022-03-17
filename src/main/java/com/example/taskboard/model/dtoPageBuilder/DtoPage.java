package com.example.taskboard.model.dtoPageBuilder;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.List;

@Schema
public class DtoPage<T>{

    @Schema(description = "List of all elements")
    @NotNull
    List<T> content;
    @Schema(description = "Total number of pages", example = "1")
    Integer totalPages;
    @Schema(description = "Total number of elements", example = "1")
    Long totalElements;

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public String toString() {
        return "DtoPage{" +
                "content=" + content +
                ", totalPages=" + totalPages +
                ", totalElements=" + totalElements +
                '}';
    }
}
