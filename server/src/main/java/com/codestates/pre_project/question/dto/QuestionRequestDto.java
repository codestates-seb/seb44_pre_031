package com.codestates.pre_project.question.dto;

import com.codestates.pre_project.question.entity.Question;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionRequestDto {
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;

    public Question toEntity() {
        return Question.questionOf(title, content);
    }
}
