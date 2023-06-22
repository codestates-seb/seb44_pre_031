package com.codestates.pre_project.module.tag.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagResponse {
    private String name;
    private Long questionCount;
    private LocalDateTime tagCreatedAt;

    @QueryProjection
    public TagResponse(String name, Long questionCount, LocalDateTime tagCreatedAt) {
        this.name = name;
        this.questionCount = questionCount;
        this.tagCreatedAt = tagCreatedAt;
    }


}
