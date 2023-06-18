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
    private Long voteCount;
    private int answerCount;
    private boolean selectedAnswer;
    private Long viewCount;
    private LocalDateTime questionCreatedAt;
    private LocalDateTime questionUpdatedAt;
    private String displayName;

    @QueryProjection
    public QuestionResponse(Long questionId, String title, String content, Long voteCount, int answerCount, boolean selectedAnswer, Long viewCount, LocalDateTime questionCreatedAt, LocalDateTime questionUpdatedAt, String displayName) {
        this.questionId = questionId;
        this.title = title;
        this.content = content;
        this.voteCount = voteCount;
        this.answerCount = answerCount;
        this.selectedAnswer = selectedAnswer;
        this.viewCount = viewCount;
        this.questionCreatedAt = questionCreatedAt;
        this.questionUpdatedAt = questionUpdatedAt;
        this.displayName = displayName;
    }
}
