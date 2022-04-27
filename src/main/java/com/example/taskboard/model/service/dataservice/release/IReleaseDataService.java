package com.example.taskboard.model.service.dataservice.release;

import com.example.taskboard.entity.release.Release;
import com.example.taskboard.entity.release.dto.ReleaseDtoRequest;
import com.example.taskboard.entity.release.dto.ReleaseDtoResponse;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IReleaseDataService {
    List<ReleaseDtoResponse> findAll();
    DtoPage<ReleaseDtoResponse> findAllPageable(Pageable pageable);
    ReleaseDtoResponse findById(UUID id);
    Release findByIdNoConvert(UUID id);
    Boolean deleteById(UUID id);
    ReleaseDtoResponse create(ReleaseDtoRequest releaseDtoRequest);
    Boolean update(UUID id, ReleaseDtoRequest releaseDtoRequest);
}
