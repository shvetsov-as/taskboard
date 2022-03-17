package com.example.taskboard.repo;

import com.example.taskboard.entity.release.Release;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Long> {
    Page<Release> findAll (Pageable pageable);
}
