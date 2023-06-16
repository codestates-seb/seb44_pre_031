package com.codestates.pre_project.module.question.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionDetailResponse {
    private Long questionId;
    private String title;
    private String content;
    private Long voteCount;
    private boolean selectedAnswer;
    private LocalDateTime questionCreatedAt;
    private String displayName;
    private int reputation;

    @QueryProjection
    public QuestionDetailResponse(Long questionId, String title, String content, Long voteCount, boolean selectedAnswer, LocalDateTime questionCreatedAt, String displayName, int reputation) {
        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.voteCount = voteCount;
        this.selectedAnswer = selectedAnswer;
        this.questionCreatedAt = questionCreatedAt;
        this.displayName = displayName;
        this.reputation = reputation;
    }
}
