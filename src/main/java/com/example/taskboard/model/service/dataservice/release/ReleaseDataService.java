package com.example.taskboard.model.service.dataservice.release;

import com.example.taskboard.entity.release.Release;
import com.example.taskboard.entity.release.dto.ReleaseDtoRequest;
import com.example.taskboard.entity.release.dto.ReleaseDtoResponse;
import com.example.taskboard.model.dataexeptions.DataNotFoundException;
import com.example.taskboard.model.dataexeptions.ElementNotFoundException;
import com.example.taskboard.model.dtoPageBuilder.DtoPage;
import com.example.taskboard.model.dtoPageBuilder.DtoPageBuilder;
import com.example.taskboard.model.service.converter.request.DtoReleaseConverter;
import com.example.taskboard.model.service.converter.response.ReleaseConverter;
import com.example.taskboard.repo.ReleaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReleaseDataService implements IReleaseDataService {
    private final ReleaseRepository releaseRepository;
    private final ReleaseConverter releaseConverter;
    private final DtoReleaseConverter dtoReleaseConverter;

    public ReleaseDataService(ReleaseRepository releaseRepository, ReleaseConverter releaseConverter, DtoReleaseConverter dtoReleaseConverter) {
        this.releaseRepository = releaseRepository;
        this.releaseConverter = releaseConverter;
        this.dtoReleaseConverter = dtoReleaseConverter;
    }

    @Override
    public List<ReleaseDtoResponse> findAll() {
        List<Release> releaseList = releaseRepository.findAll();
        if (releaseList.size() == 0) throw new DataNotFoundException();
        return releaseConverter.convertToDto(releaseList);
    }

    @Override
    public DtoPage<ReleaseDtoResponse> findAllPageable(Pageable pageable) {
        Page<Release> releasePage = releaseRepository.findAll(pageable);
        if (releasePage.getContent().size() == 0) throw new DataNotFoundException();
        List<ReleaseDtoResponse> releaseDtoResponseList = releaseConverter.convertToDto(releasePage.getContent());
        return new DtoPageBuilder<ReleaseDtoResponse>()
                .setContent(releaseDtoResponseList)
                .setTotalPages(releasePage.getTotalPages())
                .setTotalElements(releasePage.getTotalElements())
                .build();
    }

    @Override
    public ReleaseDtoResponse findById(Long id) {
        Optional<Release> release = releaseRepository.findById(id);
        return releaseConverter.convertToDto(release.orElseThrow(() -> new ElementNotFoundException(id)));
    }

    @Override
    public Boolean deleteById(Long id) {
        if (releaseRepository.existsById(id)) {
            releaseRepository.deleteById(id);
            return true;
        } else throw new ElementNotFoundException(id);
    }

    @Override
    public ReleaseDtoResponse create(ReleaseDtoRequest releaseDtoRequest) {
        Release release = dtoReleaseConverter.convertToEntity(releaseDtoRequest);
        release = releaseRepository.save(release);
        return findById(release.getRelId());
    }

    @Override
    public Boolean update(Long id, ReleaseDtoRequest releaseDtoRequest) {
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
    public Release findByIdNoConvert(Long id) {
        Optional<Release> release = releaseRepository.findById(id);
        return release.orElseThrow(() -> new ElementNotFoundException(id));
    }
}
