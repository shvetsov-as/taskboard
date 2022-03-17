package com.example.taskboard.model.dtoPageBuilder;

import java.util.List;

public class DtoPageBuilder<T> implements IDtoPageBuilder<T> {

    private DtoPage<T> dtoPage = new DtoPage<T>();

    @Override
    public IDtoPageBuilder<T> setContent(List<T> content) {
        dtoPage.content = content;
        return this;
    }

    @Override
    public IDtoPageBuilder<T> setTotalPages(Integer totalPages) {
        dtoPage.totalPages = totalPages;
        return this;
    }

    @Override
    public IDtoPageBuilder<T> setTotalElements(Long totalElements) {
        dtoPage.totalElements = totalElements;
        return this;
    }

    @Override
    public DtoPage build() {
        return dtoPage;
    }
}
