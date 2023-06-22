package com.codestates.pre_project.module.tag.repository;

import com.codestates.pre_project.module.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
