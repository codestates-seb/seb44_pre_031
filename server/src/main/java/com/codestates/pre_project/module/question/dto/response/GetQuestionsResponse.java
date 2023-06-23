package com.codestates.pre_project.module.question.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetQuestionsResponse {
    private QuestionResponse question;
    private List<String> tags;

    public GetQuestionsResponse(QuestionResponse question, List<String> tags) {
        this.question = question;
        this.tags = tags;
    }
}
