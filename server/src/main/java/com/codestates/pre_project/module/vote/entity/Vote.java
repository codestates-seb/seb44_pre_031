package com.codestates.pre_project.module.vote.entity;

import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.question.entity.Question;
import lombok.*;

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

    private Vote(Question question, Member member, int voteType) {
        this.question = question;
        this.member = member;
        this.voteType = voteType;
    }

    public static Vote of(Question question, Member member, int voteType) {
        return new Vote(question, member, voteType);
    }
}
