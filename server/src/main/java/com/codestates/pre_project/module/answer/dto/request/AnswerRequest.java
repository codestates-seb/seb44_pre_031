package com.codestates.pre_project.module.answer.dto.request;

import com.codestates.pre_project.module.answer.entity.Answer;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerRequest {
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    public Answer toEntity() {
        return Answer.builder()
                .content(content)
                .build();
    }
}
