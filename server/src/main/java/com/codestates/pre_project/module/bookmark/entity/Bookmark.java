package com.codestates.pre_project.module.bookmark.entity;

import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.module.question.entity.Question;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "bookmarks")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Question question;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;
    @Column(nullable = false)
    private boolean status;

    public Bookmark(Question question, Member member) {
        this.question = question;
        this.member = member;
        this.status = true;
    }

    public void cancelBookmark(Question question) {
        this.status = false;
        question.cancelBookmark();
    }
}
