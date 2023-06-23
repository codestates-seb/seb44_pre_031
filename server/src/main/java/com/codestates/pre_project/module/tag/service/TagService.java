package com.codestates.pre_project.module.tag.service;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.module.question.service.QuestionTagService;
import com.codestates.pre_project.module.tag.dto.response.TagResponse;
import com.codestates.pre_project.module.tag.entity.Tag;
import com.codestates.pre_project.module.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.codestates.pre_project.global.exception.ErrorCode.TAG_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {
    private final TagRepository tagRepository;
    private final QuestionTagService questionTagService;

    public List<Tag> addTags(String[] tagList) {
        List<Tag> tags = new ArrayList<>();
        for (String tag : tagList) {
            tags.add(registerTag(tag));
        }
        return tags;
    }

    private Tag registerTag(String tagString) {
        Optional<Tag> tag = tagRepository.findByName(tagString);
        if (tag.isEmpty()) {
            return tagRepository.save(Tag.from(tagString));
        }
        return tag.get();
    }

    @Transactional(readOnly = true)
    public List<TagResponse> getTags() {
        List<Tag> tags = tagRepository.findAll();
        List<Long> tagIds = tagsToTagIds(tags);

        return tagRepository.getAllTagResponses(tagIds);
    }

    private List<Long> tagsToTagIds(List<Tag> tags) {
        List<Long> result = new ArrayList<>();
        for (Tag tag : tags) {
            result.add(tag.getId());
        }

        return result;
    }

    public List<Long> getQuestionIdsByTag(String tag) {
        Long tagId = tagRepository.findByName(tag)
                .orElseThrow(() -> new CustomException(TAG_NOT_FOUND)).getId();

        return questionTagService.findQuestionIds(tagId);
    }
}
