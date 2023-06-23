package com.codestates.pre_project.module.question.dto.response;

import com.codestates.pre_project.module.answer.dto.response.AnswerResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetQuestionDetailResponse {
    private QuestionDetailResponse question;
    private Page<AnswerResponse> answers;
    private List<String> tags;

    public GetQuestionDetailResponse(QuestionDetailResponse question, Page<AnswerResponse> answers, List<String> tags) {
        this.question = question;
        this.answers = answers;
        this.tags = tags;
    }
}
