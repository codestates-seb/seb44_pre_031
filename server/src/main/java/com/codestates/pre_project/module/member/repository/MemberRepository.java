package com.codestates.pre_project.module.member.repository;

import com.codestates.pre_project.module.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    Optional<Member> findByDisplayName(String displayName);
    Optional<Member> findByEmail(String email);
}
