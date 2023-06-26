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
    private Integer answerCount;
    private LocalDateTime questionCreatedAt;
    private LocalDateTime questionUpdatedAt;
    private Long memberId;
    private String displayName;
    private int reputation;

    @QueryProjection
    public QuestionDetailResponse(Long questionId, String title, String content, Long likeCount, Long viewCount, boolean selectedAnswer, Integer answerCount, LocalDateTime questionCreatedAt, LocalDateTime questionUpdatedAt, Long memberId, String displayName, int reputation) {
        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.viewCount = viewCount;
        this.selectedAnswer = selectedAnswer;
        this.answerCount = answerCount;
        this.questionCreatedAt = questionCreatedAt;
        this.questionUpdatedAt = questionUpdatedAt;
        this.memberId = memberId;
        this.displayName = displayName;
        this.reputation = reputation;
    }
}
