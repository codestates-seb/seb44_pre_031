package com.codestates.pre_project.question.repository;

import com.codestates.pre_project.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
