package com.codestates.pre_project.module.question.dto.response;

import com.codestates.pre_project.module.answer.dto.response.AnswerResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetQuestionResponse {
    private QuestionDetailResponse question;
    private Page<AnswerResponse> answers;

    public GetQuestionResponse(QuestionDetailResponse question, Page<AnswerResponse> answers) {
        this.question = question;
        this.answers = answers;
    }
}
