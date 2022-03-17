package com.example.taskboard.model.service.converter.request;

import com.example.taskboard.entity.release.Release;
import com.example.taskboard.entity.release.dto.ReleaseDtoRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoReleaseConverter {

    public Release convertToEntity (ReleaseDtoRequest releaseDtoRequest){
        return new Release(releaseDtoRequest.getRelVersion(), releaseDtoRequest.getRelDateFrom(), releaseDtoRequest.getRelDateTo());
    }

    public List<Release> convertToEntity (List<ReleaseDtoRequest> releaseDtoRequest){
        return releaseDtoRequest.stream().map(this::convertToEntity).collect(Collectors.toList());
    }
}
