package com.codestates.pre_project.answer.entity;

import com.codestates.pre_project.base.BaseEntity;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.question.entity.Question;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "answers")
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private Long id;
    @Column(name = "body", nullable = false)
    private String content;
    @Column(name = "select")
    private boolean select;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Builder
    public Answer(String content, boolean select) {
        this.content = content;
        this.select = select;
    }

    public void selected() {
        this.select = true;
    }
}