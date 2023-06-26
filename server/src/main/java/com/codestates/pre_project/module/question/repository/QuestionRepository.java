package com.codestates.pre_project.module.question.repository;

import com.codestates.pre_project.module.answer.entity.Answer;
import com.codestates.pre_project.module.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long>, QuestionRepositoryCustom {
}
