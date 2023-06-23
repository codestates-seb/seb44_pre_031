package com.codestates.pre_project.module.tag.entity;

import com.codestates.pre_project.module.base.BaseEntity;
import com.codestates.pre_project.module.question.entity.QuestionTag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "tag_name")
    private String name;

    @Builder
    private Tag(String name) {
        this.name = name;
    }

    public static Tag from(String name) {
        return Tag.builder()
                .name(name)
                .build();
    }
}
