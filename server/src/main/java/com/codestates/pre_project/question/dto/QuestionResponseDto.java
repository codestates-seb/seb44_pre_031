package com.codestates.pre_project.question.dto;

import com.codestates.pre_project.question.entity.Question;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class QuestionResponseDto {
    private Long id;
    private String title;
    private String content;

    public QuestionResponseDto toResponse(Question question) {
        return QuestionResponseDto.builder()
                .id(question.getId())
                .title(question.getTitle())
                .content(question.getContent())
                .build();
    }
}
