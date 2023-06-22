package com.codestates.pre_project.module.question.service;

import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.entity.QuestionTag;
import com.codestates.pre_project.module.question.repository.QuestionTagRepository;
import com.codestates.pre_project.module.tag.entity.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionTagService {
    private final QuestionTagRepository questionTagRepository;

    public void save(Question question, List<Tag> tags) {
        for (Tag tag : tags) {
            QuestionTag questionTag = QuestionTag.builder()
                    .question(question)
                    .tag(tag)
                    .build();
            questionTagRepository.save(questionTag);
        }
    }
}
