package com.codestates.pre_project.module.answer.service;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.service.MemberService;
import com.codestates.pre_project.module.answer.entity.Answer;
import com.codestates.pre_project.module.answer.repository.AnswerRepository;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.pre_project.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final MemberService memberService;
    private final QuestionService questionService;

    @Transactional
    public void createAnswer(Long memberId, Long questionId, Answer request) {
        Member member = memberService.findMember(memberId);
        Question question = questionService.getQuestion(questionId);
        answerRepository.save(Answer.of(request.getContent(), member, question));
    }

    @Transactional
    public void updateAnswer(Long answerId, Answer request) {
        Answer answer = findAnswerById(answerId);
        answer.update(request);
    }

    public void selectAnswer(Long questionId, Long answerId) {
        Question question = questionService.getQuestion(questionId);
        questionService.checkExistSelectedAnswer(question);
        question.selectAnswer();

        Answer answer = findAnswerById(answerId);
        answer.select();
    }


    public void deleteAnswer(Long answerId) {
        Answer answer = findAnswerById(answerId);
        answerRepository.delete(answer);
    }



    private Answer findAnswerById(Long answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new CustomException(ANSWER_NOT_FOUND));
    }
}
