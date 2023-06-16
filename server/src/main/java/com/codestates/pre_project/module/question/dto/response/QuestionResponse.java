package com.codestates.pre_project.module.question.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionResponse {
    private String title;
    private String content;
    private Long voteCount;
    private boolean selectedAnswer;
}
