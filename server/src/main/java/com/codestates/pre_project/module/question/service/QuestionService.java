package com.codestates.pre_project.module.question.service;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.service.MemberService;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.codestates.pre_project.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberService memberService;

    @Transactional
    public void createQuestion(Long memberId, Question request) {
        Member member = memberService.findMember(memberId);
        questionRepository.save(Question.of(member, request));
    }

    @Transactional
    public void updateQuestion(Long questionId, Question request) {
        Question question = findById(questionId);
        question.update(request);
    }

    public Question getQuestion(Long questionId) {
        Question question = findById(questionId);

        return question;
    }

    public List<Question> getQuestions() {

        return questionRepository.findAll();
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    private Question findById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException(QUESTION_NOT_FOUND));
    }
}
