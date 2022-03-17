package com.example.taskboard.repo;

import com.example.taskboard.entity.taskboard.Taskboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskboardRepository extends JpaRepository<Taskboard, Long> {
    Page<Taskboard> findAll (Pageable pageable);
}