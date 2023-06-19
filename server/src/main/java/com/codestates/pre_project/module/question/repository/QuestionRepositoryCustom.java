package com.codestates.pre_project.module.question.repository;

import com.codestates.pre_project.module.question.dto.response.GetQuestionResponse;
import com.codestates.pre_project.module.question.dto.response.QuestionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionRepositoryCustom {
    GetQuestionResponse getQuestionWithAnswer(Long questionId, Pageable pageable);
    Page<QuestionResponse> getQuestions(Pageable pageable);
}
