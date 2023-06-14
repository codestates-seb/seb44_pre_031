package com.codestates.pre_project.question.entity;

import com.codestates.pre_project.answer.entity.Answer;
import com.codestates.pre_project.base.BaseEntity;
import com.codestates.pre_project.member.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;
    @Column
    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(name = "view_count")
    private Long viewCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @OneToMany(mappedBy = "questions", cascade = CascadeType.PERSIST)
    private List<Answer> answers = new ArrayList<>();

    @Builder
    public Question(String title, String content, Long viewCount) {
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }
}
