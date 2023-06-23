package com.codestates.pre_project.module.question.dto.response;

import com.codestates.pre_project.module.answer.dto.response.AnswerResponse;
import com.codestates.pre_project.module.tag.dto.response.TagResponse;
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
    private List<TagResponse> tags;

    public GetQuestionDetailResponse(QuestionDetailResponse question, Page<AnswerResponse> answers, List<TagResponse> tags) {
        this.question = question;
        this.answers = answers;
        this.tags = tags;
    }
}
