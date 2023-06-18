package com.codestates.pre_project.module.question.entity;

import com.codestates.pre_project.module.answer.entity.Answer;
import com.codestates.pre_project.module.base.BaseEntity;
import com.codestates.pre_project.module.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id", nullable = false)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;
    @Column(name = "view_count")
    private Long viewCount;
    @Column(name = "selected_answer")
    private boolean selectedAnswer;
    @Column(name = "vote_count")
    private Long voteCount;
    @Column(name = "bookmark_count")
    private Long bookmarkCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    private List<Answer> answers = new ArrayList<>();

    @Builder
    private Question(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.viewCount = 0L;
        this.voteCount = 0L;
        this.bookmarkCount = 0L;
    }

    public static Question of(Member member, Question request) {
        return Question.builder()
                .member(member)
                .title(request.getTitle())
                .content(request.getContent())
                .build();
    }

    public void update(Question question) {
        this.title = question.title;
        this.content = question.content;
    }

    public void selectAnswer() {
        this.selectedAnswer = true;
    }

    public void view() {
        this.viewCount++;
    }

    public void vote() {
        this.voteCount++;
    }

    public void cancelVote() {
        this.voteCount--;
    }

    public void bookmark() {
        this.bookmarkCount++;
    }

    public void cancelBookmark() {
        this.bookmarkCount--;
    }
}
