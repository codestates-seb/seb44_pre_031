package com.codestates.pre_project.module.question.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "tag_name")
    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.PERSIST)
    private List<QuestionTag> questionTags = new ArrayList<>();

    @Builder
    private Tag(String name) {
        this.name = name;
    }

    public static Tag from(String name) {
        return Tag.builder()
                .name(name)
                .build();
    }

    public void addQuestionTag(QuestionTag questionTag) {
        questionTags.add(questionTag);
        questionTag.setTag(this);
    }
}
