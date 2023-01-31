package com.isoft.code.stackoverflowclone.repository;

import com.isoft.code.stackoverflowclone.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    List<Users> findAllByEmailOrNameContainingIgnoreCase(String email, String name);
}
