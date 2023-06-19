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
    private Long likeCount;
    private Long viewCount;
    private boolean selectedAnswer;
    private LocalDateTime questionCreatedAt;
    private String displayName;
    private int reputation;

    @QueryProjection
    public QuestionDetailResponse(Long questionId, String title, String content, Long likeCount, Long viewCount, boolean selectedAnswer, LocalDateTime questionCreatedAt, String displayName, int reputation) {
        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.selectedAnswer = selectedAnswer;
        this.questionCreatedAt = questionCreatedAt;
        this.displayName = displayName;
        this.reputation = reputation;
    }
}
