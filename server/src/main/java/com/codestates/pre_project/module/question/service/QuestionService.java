package com.codestates.pre_project.module.question.service;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.service.MemberService;
import com.codestates.pre_project.module.question.dto.response.GetQuestionResponse;
import com.codestates.pre_project.module.question.dto.response.QuestionResponse;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.repository.QuestionRepository;
import com.codestates.pre_project.module.tag.entity.Tag;
import com.codestates.pre_project.module.tag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final TagService tagService;
    private final QuestionTagService questionTagService;

    @Transactional
    public Long createQuestion(Long memberId, Question request, String[] tagList) {
        Member member = memberService.findMember(memberId);
        Question question = questionRepository.save(Question.of(member, request));

        List<Tag> tags = tagService.addTags(tagList);

        questionTagService.save(question, tags);

        return question.getId();
    }

    @Transactional
    public void updateQuestion(Long questionId, Long memberId, Question request) {
        Question question = findQuestionById(questionId);
        validateAuthorEqualMember(question.getMember().getId(), memberId);

        question.update(request);
    }

    @Transactional
    public GetQuestionResponse getQuestion(Long questionId, Pageable pageable) {
        Question question = findQuestionById(questionId);
        question.view();

        return questionRepository.getQuestionWithAnswer(questionId, pageable);
    }

    public Page<QuestionResponse> getQuestions(Pageable pageable) {

        return questionRepository.getQuestions(pageable);
    }

    public Page<QuestionResponse> getQuestionsWithKeyword(String keyword, Pageable pageable) {

        return questionRepository.getQuestionsWithTitle(keyword, pageable);
    }

    public Page<QuestionResponse> getQuestionsWithAuthor(String author, Pageable pageable) {

        return questionRepository.getQuestionsWithAuthor(author, pageable);
    }

    public Page<QuestionResponse> getUnansweredQuestions(Pageable pageable) {

        return questionRepository.getUnansweredQuestions(pageable);
    }

    @Transactional
    public void deleteQuestion(Long questionId, Long memberId) {
        Question question = findQuestionById(questionId);
        validateAuthorEqualMember(question.getMember().getId(), memberId);

        questionRepository.delete(question);
    }

    public void checkExistSelectedAnswer(Question question) {
        if (question.isSelectedAnswer()) {
            throw new CustomException(ALREADY_SELECTED_ANSWER);
        }
    }

    private void validateAuthorEqualMember(Long authorId, Long memberId) {
        if (!authorId.equals(memberId)) {
            throw new CustomException(NONE_AUTHORIZATION_TOKEN);
        }
    }

    public Question findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException(QUESTION_NOT_FOUND));
    }
}
