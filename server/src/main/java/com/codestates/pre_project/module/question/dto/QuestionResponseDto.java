package com.codestates.pre_project.module.question.dto;

import com.codestates.pre_project.module.question.entity.Question;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<QuestionResponseDto> toResponses(List<Question> questions) {
        return questions.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
