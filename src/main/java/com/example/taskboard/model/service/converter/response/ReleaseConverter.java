package com.example.taskboard.model.service.converter.response;

import com.example.taskboard.entity.release.dto.ReleaseDtoResponse;
import com.example.taskboard.entity.release.Release;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReleaseConverter {

    public ReleaseDtoResponse convertToDto (Release release){
        return new ReleaseDtoResponse(release.getRelId(),
                release.getRelVersion(), release.getRelDateFrom(), release.getRelDateTo());
    }

    public List<ReleaseDtoResponse> convertToDto (List<Release> releases){
        return releases.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
