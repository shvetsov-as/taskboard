package com.example.taskboard.entity.release.mapper;

import com.example.taskboard.entity.release.Release;
import com.example.taskboard.entity.release.dto.ReleaseDtoRequest;
import com.example.taskboard.entity.release.dto.ReleaseDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReleaseMapper {

    Release releaseDtoRequestToRelease(ReleaseDtoRequest releaseDtoRequest);

    ReleaseDtoResponse releaseToReleaseDtoResponse(Release release);

    List<ReleaseDtoResponse> releaseListToReleaseDtoResponseList(List<Release> release);
}
