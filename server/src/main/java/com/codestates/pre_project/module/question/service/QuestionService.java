package com.codestates.pre_project.module.question.service;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.service.MemberService;
import com.codestates.pre_project.module.question.dto.response.GetQuestionResponse;
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
        Question question = findQuestionById(questionId);
        question.update(request);
    }

    public GetQuestionResponse getQuestion(Long questionId) {
        Question question = findQuestionById(questionId);
        question.view();

        return questionRepository.getQuestionWithAnswer(questionId);
    }

    public List<Question> getQuestions() {

        return questionRepository.findAll();
    }

    @Transactional
    public void deleteQuestion(Long questionId) {
        Question question = findQuestionById(questionId);
        questionRepository.delete(question);
    }

    public void checkExistSelectedAnswer(Question question) {
        if (question.isSelectedAnswer()) {
            throw new CustomException(ALREADY_SELECTED_ANSWER);
        }
    }

    public Question findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException(QUESTION_NOT_FOUND));
    }
}
