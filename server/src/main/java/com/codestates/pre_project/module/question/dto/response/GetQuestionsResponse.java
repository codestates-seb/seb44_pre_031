package com.codestates.pre_project.module.question.dto.response;

import com.codestates.pre_project.module.tag.dto.response.TagResponse;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetQuestionsResponse {
    private QuestionResponse question;
    private List<TagResponse> tags;

    public GetQuestionsResponse(QuestionResponse question, List<TagResponse> tags) {
        this.question = question;
        this.tags = tags;
    }
}
