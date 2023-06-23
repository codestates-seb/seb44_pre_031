package com.codestates.pre_project.module.question.repository;

import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.entity.QuestionTag;
import com.codestates.pre_project.module.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, Long> {
    List<QuestionTag> findByTagId(Long tagId);
    List<QuestionTag> findByQuestionId(Long questionId);
}
