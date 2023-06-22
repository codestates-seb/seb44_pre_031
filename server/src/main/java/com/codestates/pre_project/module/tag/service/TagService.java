package com.codestates.pre_project.module.tag.service;

import com.codestates.pre_project.module.tag.entity.Tag;
import com.codestates.pre_project.module.tag.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> addTags(List<String> tagList) {
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
}
