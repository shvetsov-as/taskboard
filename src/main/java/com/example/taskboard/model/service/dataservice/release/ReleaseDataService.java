package com.example.taskboard.model.service.dataservice.release;

import com.example.taskboard.entity.release.Release;
import com.example.taskboard.entity.release.dto.ReleaseDtoRequest;
import com.example.taskboard.entity.release.dto.ReleaseDtoResponse;
import com.example.taskboard.entity.release.mapper.ReleaseMapper;
import com.example.taskboard.model.dataexeptions.DataNotFoundException;
import com.example.taskboard.model.dataexeptions.ElementNotFoundException;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.dtoPageBuilder.DtoPageBuilder;
import com.example.taskboard.repo.ReleaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReleaseDataService implements IReleaseDataService {
    private final ReleaseRepository releaseRepository;
    private final ReleaseMapper releaseMapper;

    public ReleaseDataService(ReleaseRepository releaseRepository,
                              ReleaseMapper releaseMapper) {
        this.releaseRepository = releaseRepository;
        this.releaseMapper = releaseMapper;
    }

    @Override
    public List<ReleaseDtoResponse> findAll() {
        List<Release> releaseList = releaseRepository.findAll();
        if (releaseList.isEmpty()) throw new DataNotFoundException();
        return releaseMapper.releaseListToReleaseDtoResponseList(releaseList);
    }

    @Override
    public DtoPage<ReleaseDtoResponse> findAllPageable(Pageable pageable) {
        Page<Release> releasePage = releaseRepository.findAll(pageable);
        if (releasePage.getContent().isEmpty()) throw new DataNotFoundException();
        List<ReleaseDtoResponse> releaseDtoResponseList = releaseMapper.releaseListToReleaseDtoResponseList(releasePage.getContent());
        return new DtoPageBuilder<ReleaseDtoResponse>()
                .setContent(releaseDtoResponseList)
                .setTotalPages(releasePage.getTotalPages())
                .setTotalElements(releasePage.getTotalElements())
                .build();
    }

    @Override
    public ReleaseDtoResponse findById(UUID id) {
        Optional<Release> release = releaseRepository.findById(id);
        return releaseMapper.releaseToReleaseDtoResponse(release.orElseThrow(() -> new ElementNotFoundException(id)));
    }

    @Override
    @Transactional
    public Boolean deleteById(UUID id) {
        if (!releaseRepository.existsById(id)) throw new ElementNotFoundException(id);
            releaseRepository.deleteById(id);
            return true;
    }

    @Override
    public ReleaseDtoResponse create(ReleaseDtoRequest releaseDtoRequest) {
        Release release = releaseMapper.releaseDtoRequestToRelease(releaseDtoRequest);

        release.setRelId(UUID.randomUUID());
        release = releaseRepository.save(release);

        return findById(release.getRelId());
    }

    @Override
    @Transactional
    public Boolean update(UUID id, ReleaseDtoRequest releaseDtoRequest) {
        Release release = findByIdNoConvert(id);

        if(releaseDtoRequest.getRelVersion() != null){
            release.setRelVersion(releaseDtoRequest.getRelVersion());
        }

        if(releaseDtoRequest.getRelDateFrom() != null){
            release.setRelDateFrom(releaseDtoRequest.getRelDateFrom());
        }

        if(releaseDtoRequest.getRelDateTo() != null){
            release.setRelDateTo(releaseDtoRequest.getRelDateTo());
        }

        releaseRepository.save(release);
        return true;
    }

    @Override
    public Release findByIdNoConvert(UUID id) {
        Optional<Release> release = releaseRepository.findById(id);
        return release.orElseThrow(() -> new ElementNotFoundException(id));
    }
}
