package com.codestates.pre_project.module.question.service;

import com.codestates.pre_project.module.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionTagService {
    @Transactional
    public void addTagToQuestion(Question question, List<String> tagList) {
        for (String tag : tagList) {

        }
    }
}
