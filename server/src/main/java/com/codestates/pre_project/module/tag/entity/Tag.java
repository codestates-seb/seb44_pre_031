package com.codestates.pre_project.module.tag.entity;

import com.codestates.pre_project.module.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tags")
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;
    @Column(name = "tag_name", nullable = false)
    private String tagName;
    @Column(name = "tag_description")
    private String tagDescription;

    private Tag(String tagName, String tagDescription) {
        this.tagName = tagName;
        this.tagDescription = tagDescription;
    }

    public static Tag of(String tagName, String tagDescription) {
        return new Tag(tagName, tagDescription);
    }
}
