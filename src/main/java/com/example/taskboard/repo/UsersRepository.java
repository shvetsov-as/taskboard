package com.example.taskboard.repo;

import com.example.taskboard.entity.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findUsersByUserLogin(String login);
    Page<Users> findAll (Pageable pageable);
}