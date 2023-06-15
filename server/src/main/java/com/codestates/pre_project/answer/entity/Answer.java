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
    private boolean selected;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    private Answer(String content) {
        this.content = content;
    }

    public static Answer answerFrom(String content) {
        return new Answer(content);
    }

    public void update(Answer answer) {
        this.content = answer.content;
    }

    public void select() {
        this.selected = true;
    }
}
