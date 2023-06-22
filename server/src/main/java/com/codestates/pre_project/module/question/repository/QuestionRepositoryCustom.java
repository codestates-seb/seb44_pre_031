package com.codestates.pre_project.module.question.repository;

import com.codestates.pre_project.module.question.dto.response.GetQuestionResponse;
import com.codestates.pre_project.module.question.dto.response.QuestionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionRepositoryCustom {
    GetQuestionResponse getQuestionWithAnswer(Long questionId, Pageable pageable);
    Page<QuestionResponse> getQuestions(Pageable pageable);
    Page<QuestionResponse> getQuestionsWithAuthor(String author, Pageable pageable);
    Page<QuestionResponse> getQuestionsWithTitle(String keyword, Pageable pageable);
    Page<QuestionResponse> getUnansweredQuestions(Pageable pageable);
    Page<QuestionResponse> getQuestionsWithTag(List<Long> questionIds, Pageable pageable);
}
