package com.codestates.pre_project.module.question.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tags")
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "tag_name")
    private String name;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Builder
    private QuestionTag(String name, Question question) {
        this.name = name;
        this.question = question;
    }

    public static QuestionTag of(String name, Question question) {
        return QuestionTag.builder()
                .name(name)
                .question(question)
                .build();
    }
}
