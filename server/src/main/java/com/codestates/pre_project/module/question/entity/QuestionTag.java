package com.codestates.pre_project.module.question.entity;

import com.codestates.pre_project.module.tag.entity.Tag;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "question_tag")
@Setter
public class QuestionTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_tag_id", nullable = false, updatable = false)
    private Long id;

    @OnDelete(action= OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Builder
    private QuestionTag(Question question, Tag tag) {
        this.question = question;
        this.tag = tag;
    }
}
