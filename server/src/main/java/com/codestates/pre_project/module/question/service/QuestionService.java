package com.codestates.pre_project.module.question.service;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.service.MemberService;
import com.codestates.pre_project.module.question.dto.response.GetQuestionResponse;
import com.codestates.pre_project.module.question.dto.response.QuestionResponse;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.pre_project.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberService memberService;

    @Transactional
    public Long createQuestion(Long memberId, Question request) {
        Member member = memberService.findMember(memberId);
        Question question = questionRepository.save(Question.of(member, request));

        return question.getId();
    }

    @Transactional
    public void updateQuestion(Long questionId, Long memberId, Question request) {
        Question question = findQuestionById(questionId);
        validateMemberMatch(question.getMember().getId(), memberId);

        question.update(request);
    }

    public GetQuestionResponse getQuestion(Long questionId) {
        Question question = findQuestionById(questionId);
        question.view();

        return questionRepository.getQuestionWithAnswer(questionId);
    }

    public Page<QuestionResponse> getQuestions(Pageable pageable) {

        return questionRepository.getQuestions(pageable);
    }

    @Transactional
    public void deleteQuestion(Long questionId, Long memberId) {
        Question question = findQuestionById(questionId);
        validateMemberMatch(question.getMember().getId(), memberId);

        questionRepository.delete(question);
    }

    @Transactional
    public void likeQuestion(Long questionId, int likeStatus) {
        Question question = findQuestionById(questionId);
        // TODO: Question, Vote 매핑 다시하고 완성
        question.like();
    }

    public void checkExistSelectedAnswer(Question question) {
        if (question.isSelectedAnswer()) {
            throw new CustomException(ALREADY_SELECTED_ANSWER);
        }
    }

    private void validateMemberMatch(Long authorId, Long memberId) {
        if (!authorId.equals(memberId)) {
            throw new CustomException(NONE_AUTHORIZATION_TOKEN);
        }
    }

    public Question findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException(QUESTION_NOT_FOUND));
    }

}
