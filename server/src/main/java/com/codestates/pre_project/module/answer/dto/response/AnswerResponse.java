package com.codestates.pre_project.module.answer.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerResponse {
    private Long questionId;
    private Long answerId;
    private String content;
    private boolean selected;
    private Long voteCount;
    private LocalDateTime answerCreatedAt;
    private LocalDateTime answerUpdatedAt;
    private Long memberId;
    private String displayName;
    private int reputation;

    @QueryProjection
    public AnswerResponse(Long questionId, Long answerId, String content, boolean selected, Long voteCount, LocalDateTime answerCreatedAt, LocalDateTime answerUpdatedAt, Long memberId, String displayName, int reputation) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.content = content;
        this.selected = selected;
        this.voteCount = voteCount;
        this.answerCreatedAt = answerCreatedAt;
        this.answerUpdatedAt = answerUpdatedAt;
        this.memberId = memberId;
        this.displayName = displayName;
        this.reputation = reputation;
    }
}
