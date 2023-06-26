package com.codestates.pre_project.module.answer.entity;

import com.codestates.pre_project.module.answer.entity.enums.VoteStatus;
import com.codestates.pre_project.module.member.entity.Member;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import static com.codestates.pre_project.module.answer.entity.enums.VoteStatus.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "answer_like")
@Setter
public class AnswerLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_like_id", nullable = false, updatable = false)
    private Long id;

    @OnDelete(action= OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private VoteStatus voteStatus;

    @Builder
    public AnswerLike(Answer answer, Member member) {
        this.answer = answer;
        this.member = member;
        this.voteStatus = NONE;
    }
}
