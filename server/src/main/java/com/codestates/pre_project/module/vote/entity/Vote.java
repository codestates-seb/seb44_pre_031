package com.codestates.pre_project.module.vote.entity;

import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.module.question.entity.Question;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "save_id", nullable = false, updatable = false)
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;
    @Column(name = "vote_type")
    private boolean voteType;

    private Vote(Question question, Member member, boolean voteType) {
        this.question = question;
        this.member = member;
        this.voteType = voteType;
    }

    public static Vote like(Question question, Member member) {
        return new Vote(question, member, true);
    }

    public Vote dislike(Question question, Member member) {
        question.vote(question.getVoteCount() - 1);
        return new Vote(question, member, false);
    }
}
