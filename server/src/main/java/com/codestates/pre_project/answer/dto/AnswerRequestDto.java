package com.codestates.pre_project.answer.dto;

import com.codestates.pre_project.answer.entity.Answer;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerRequestDto {
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    public Answer toEntity() {
        return Answer.answerFrom(content);
    }
}
