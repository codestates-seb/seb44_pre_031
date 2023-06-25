package com.codestates.pre_project.module.question.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionResponse {
    private Long questionId;
    private String title;
    private String content;
    private Long likeCount;
    private int answerCount;
    private boolean selectedAnswer;
    private Long viewCount;
    private LocalDateTime questionCreatedAt;
    private LocalDateTime questionUpdatedAt;
    private Long memberId;
    private String displayName;

    @QueryProjection
    public QuestionResponse(Long questionId, String title, String content, Long likeCount, int answerCount, boolean selectedAnswer, Long viewCount, LocalDateTime questionCreatedAt, LocalDateTime questionUpdatedAt, Long memberId, String displayName) {
        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.answerCount = answerCount;
        this.selectedAnswer = selectedAnswer;
        this.viewCount = viewCount;
        this.questionCreatedAt = questionCreatedAt;
        this.questionUpdatedAt = questionUpdatedAt;
        this.memberId = memberId;
        this.displayName = displayName;
    }
}
