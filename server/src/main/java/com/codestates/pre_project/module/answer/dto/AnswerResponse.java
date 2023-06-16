package com.codestates.pre_project.module.answer.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerResponse {
    private Long answerId;
    private String content;
    private LocalDateTime answerCreatedAt;
    private String displayName;
    private int reputation;

    @QueryProjection
    public AnswerResponse(Long answerId, String content, LocalDateTime answerCreatedAt, String displayName, int reputation) {
        this.answerId = answerId;
        this.content = content;
        this.answerCreatedAt = answerCreatedAt;
        this.displayName = displayName;
        this.reputation = reputation;
    }
}
