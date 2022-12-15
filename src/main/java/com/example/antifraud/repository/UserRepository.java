package com.example.antifraud.repository;

import com.example.antifraud.models.entities.Authority;
import com.example.antifraud.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    public Optional<User> findUserByUsername(String username);
    public List<User> findAllByOrderByIdAsc();

}
