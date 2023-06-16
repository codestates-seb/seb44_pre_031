package com.codestates.pre_project.module.question.repository;

import com.codestates.pre_project.module.question.dto.response.GetQuestionResponse;

public interface QuestionRepositoryCustom {
    public GetQuestionResponse getQuestionWithAnswer(Long questionId, Long memberId);
}
