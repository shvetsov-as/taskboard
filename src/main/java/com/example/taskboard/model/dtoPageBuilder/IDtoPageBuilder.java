package com.example.taskboard.model.dtoPageBuilder;

import java.util.List;

public interface IDtoPageBuilder <T>{

    IDtoPageBuilder <T> setContent(List<T> content);
    IDtoPageBuilder <T> setTotalPages(Integer totalPages);
    IDtoPageBuilder <T> setTotalElements(Long totalElements);
    DtoPage build();
}

