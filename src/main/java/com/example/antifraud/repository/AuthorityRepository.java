package com.example.antifraud.repository;

import com.example.antifraud.models.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Authority findAuthorityByName(String name);
}
