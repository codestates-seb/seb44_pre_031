package com.codestates.pre_project.module.question.entity;

import com.codestates.pre_project.module.member.entity.Member;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "votes")
public class QuestionLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id", nullable = false, updatable = false)
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @Column(name = "vote_type")
    private int voteType;

    private QuestionLike(Question question, Member member, int voteType) {
        this.question = question;
        this.member = member;
        this.voteType = voteType;
    }

    public static QuestionLike of(Question question, Member member, int voteType) {
        return new QuestionLike(question, member, voteType);
    }
}
