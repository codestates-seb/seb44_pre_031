package com.codestates.pre_project.module.member.repository;

import com.codestates.pre_project.module.member.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
    Email findByEmail(String email);
}