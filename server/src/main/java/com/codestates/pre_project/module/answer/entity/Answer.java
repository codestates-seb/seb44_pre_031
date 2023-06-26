package com.codestates.pre_project.module.answer.entity;

import com.codestates.pre_project.module.base.BaseEntity;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.question.entity.Question;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "answers")
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id", nullable = false)
    private Long id;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "selected")
    private boolean selected;
    @Setter
    @Column(name = "vote_count")
    private Long voteCount;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Builder
    private Answer(String content, Member member, Question question) {
        this.content = content;
        this.member = member;
        this.question = question;
        this.voteCount = 0L;
    }

    public static Answer of(String content, Member member, Question question) {
        return Answer.builder()
                .content(content)
                .member(member)
                .question(question)
                .build();
    }

    public void update(Answer answer) {
        this.content = answer.content;
    }

    public void select() {
        this.selected = true;
    }
}
