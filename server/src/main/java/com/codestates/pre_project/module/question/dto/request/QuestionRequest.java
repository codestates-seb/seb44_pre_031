package com.codestates.pre_project.module.question.dto.request;

import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.module.question.entity.Question;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionRequest {
    private Member member;
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;

    public QuestionRequest(Member member) {
        this.member = member;
    }

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .build();
    }
}
