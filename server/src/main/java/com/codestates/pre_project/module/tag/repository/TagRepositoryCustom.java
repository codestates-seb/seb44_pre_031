package com.codestates.pre_project.module.tag.repository;

import com.codestates.pre_project.module.tag.dto.response.TagResponse;

import java.util.List;

public interface TagRepositoryCustom {
    List<TagResponse> getAllTagResponses(List<Long> tagIds);
}
