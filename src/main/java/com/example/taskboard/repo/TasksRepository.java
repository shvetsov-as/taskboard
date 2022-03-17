package com.example.taskboard.repo;

import com.example.taskboard.entity.tasks.Tasks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {
    Page<Tasks> findAll (Pageable pageable);
}