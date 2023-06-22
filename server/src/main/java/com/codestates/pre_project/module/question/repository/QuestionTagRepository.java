package com.codestates.pre_project.module.question.repository;

import com.codestates.pre_project.module.question.entity.QuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, Long> {
}
