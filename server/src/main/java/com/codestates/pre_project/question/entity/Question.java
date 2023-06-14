package com.codestates.pre_project.question.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;
    @Column
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(name = "view_count")
    private Long viewCount;
    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @Builder
    public Question(String title, String content, Long viewCount) {
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }
}
