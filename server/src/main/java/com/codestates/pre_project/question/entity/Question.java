package com.codestates.pre_project.question.entity;

import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.global.base.BaseEntity;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @OneToMany(mappedBy = "question", cascade = CascadeType.PERSIST)
    private List<Answer> answers = new ArrayList<>();

    private Question(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Question of(String title, String content) {
        return new Question(title, content);
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
