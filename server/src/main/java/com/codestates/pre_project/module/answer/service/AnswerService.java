package com.codestates.pre_project.module.answer.service;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.module.answer.entity.Answer;
import com.codestates.pre_project.module.answer.repository.AnswerRepository;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.service.MemberService;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.codestates.pre_project.global.exception.ErrorCode.ANSWER_NOT_FOUND;
import static com.codestates.pre_project.global.exception.ErrorCode.NONE_AUTHORIZATION_TOKEN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final MemberService memberService;
    private final QuestionService questionService;
    private final AnswerLikeService answerLikeService;

    @Transactional
    public void createAnswer(Long memberId, Long questionId, Answer request) {
        Member member = memberService.findMember(memberId);
        Question question = questionService.findQuestionById(questionId);
        answerRepository.save(Answer.of(request.getContent(), member, question));
    }

    @Transactional
    public void updateAnswer(Long answerId, Long memberId, Answer request) {
        Answer answer = findAnswerById(answerId);
        validateMemberMatch(answer.getMember().getId(), memberId);
        answer.update(request);
    }

    @Transactional
    public void selectAnswer(Long questionId, Long answerId, Long memberId) {
        Question question = questionService.findQuestionById(questionId);
        validateMemberMatch(question.getMember().getId(), memberId);
        questionService.checkExistSelectedAnswer(question);
        question.selectAnswer();

        Answer answer = findAnswerById(answerId);
        answer.select();
    }

    @Transactional
    public void deleteAnswer(Long answerId, Long memberId) {
        Answer answer = findAnswerById(answerId);
        validateMemberMatch(answer.getMember().getId(), memberId);
        answerRepository.delete(answer);
    }

    public Answer findAnswerById(Long answerId) {
        return answerRepository.findById(answerId)
                .orElseThrow(() -> new CustomException(ANSWER_NOT_FOUND));
    }

    private void validateMemberMatch(Long authorId, Long memberId) {
        if (!authorId.equals(memberId)) {
            throw new CustomException(NONE_AUTHORIZATION_TOKEN);
        }
    }

    public void setVoteCount(List<Answer> answers) {
        for (Answer answer : answers) {
            answer.setVoteCount(answerLikeService.calculateTotalVoted(answer.getId()));
        }
    }
}
