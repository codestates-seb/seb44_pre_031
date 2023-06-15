package com.codestates.pre_project.answer.dto;

import com.codestates.pre_project.answer.entity.Answer;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AnswerResponseDto {
    private Long id;
    private String content;
    private boolean select;

    public AnswerResponseDto toResponse(Answer answer) {
        return AnswerResponseDto.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .select(answer.isSelected())
                .build();
    }
}
