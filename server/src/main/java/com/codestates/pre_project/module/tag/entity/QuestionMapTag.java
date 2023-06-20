package com.codestates.pre_project.module.tag.entity;

import com.codestates.pre_project.module.base.BaseEntity;
import com.codestates.pre_project.module.question.entity.Question;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "question_map_tag")
public class QuestionMapTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_map_tag_id")
    private Long id;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
