package com.codestates.pre_project.module.question.dto.response;

import com.codestates.pre_project.module.answer.dto.AnswerResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetQuestionResponse {
    private QuestionDetailResponse question;
    private List<AnswerResponse> answers;

    public GetQuestionResponse(QuestionDetailResponse question, List<AnswerResponse> answers) {
        this.question = question;
        this.answers = answers;
    }
}
