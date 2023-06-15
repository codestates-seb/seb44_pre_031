package com.codestates.pre_project.module.question.entity;

import com.codestates.pre_project.module.answer.entity.Answer;
import com.codestates.pre_project.module.base.BaseEntity;
import com.codestates.pre_project.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    private List<Answer> answers = new ArrayList<>();

    private Question(Member member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }

    public static Question of(Member member, Question request) {
        return new Question(member, request.getTitle(), request.getContent());
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
}
