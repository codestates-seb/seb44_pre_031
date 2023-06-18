package com.codestates.pre_project.module.answer.dto.response;

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
    private boolean selected;
    private LocalDateTime answerCreatedAt;
    private LocalDateTime answerUpdatedAt;
    private String displayName;
    private int reputation;

    @QueryProjection
    public AnswerResponse(Long answerId, String content, boolean selected, LocalDateTime answerCreatedAt, LocalDateTime answerUpdatedAt, String displayName, int reputation) {
        this.answerId = answerId;
        this.content = content;
        this.selected = selected;
        this.answerCreatedAt = answerCreatedAt;
        this.answerUpdatedAt = answerUpdatedAt;
        this.displayName = displayName;
        this.reputation = reputation;
    }
}
