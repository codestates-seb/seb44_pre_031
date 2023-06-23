package com.codestates.pre_project.module.question.repository;

import com.codestates.pre_project.module.question.dto.response.GetQuestionDetailResponse;
import com.codestates.pre_project.module.question.dto.response.GetQuestionsResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuestionRepositoryCustom {
    GetQuestionDetailResponse getQuestionDetail(Long questionId, Pageable pageable);
    GetQuestionsResponse getQuestionAndTags(Long questionId);
    Page<GetQuestionsResponse> getQuestions(Pageable pageable);
    Page<GetQuestionsResponse> getQuestionsByAuthor(String author, Pageable pageable);
    Page<GetQuestionsResponse> getQuestionsByTitle(String keyword, Pageable pageable);
    Page<GetQuestionsResponse> getUnansweredQuestions(Pageable pageable);
    Page<GetQuestionsResponse> getQuestionsByTag(List<Long> questionIds, Pageable pageable);
}
