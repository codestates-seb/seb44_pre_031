package com.codestates.pre_project.module.question.repository.implementation;

import com.codestates.pre_project.module.answer.dto.response.AnswerResponse;
import com.codestates.pre_project.module.answer.dto.response.QAnswerResponse;
import com.codestates.pre_project.module.question.dto.response.*;
import com.codestates.pre_project.module.question.repository.QuestionRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.codestates.pre_project.module.answer.entity.QAnswer.answer;
import static com.codestates.pre_project.module.member.entity.QMember.member;
import static com.codestates.pre_project.module.question.entity.QQuestion.question;
import static com.querydsl.core.types.dsl.Expressions.asNumber;

@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public GetQuestionResponse getQuestionWithAnswer(Long questionId, Pageable pageable) {
        QuestionDetailResponse questionDetailResponse = fetchQuestionResponse(questionId);
        Page<AnswerResponse> answerResponses = fetchAnswerResponses(questionId, pageable);

        return new GetQuestionResponse(questionDetailResponse, answerResponses);
    }

    @Override
    public Page<QuestionResponse> getQuestions(Pageable pageable) {
        List<QuestionResponse> result = getQuestion()
                .orderBy(question.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(question.id)
                .from(question);

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<QuestionResponse> getQuestionsWithAuthor(String author, Pageable pageable) {
        List<QuestionResponse> result = getQuestion()
                .where(question.member.displayName.eq(author))
                .orderBy(question.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(question.id)
                .from(question)
                .where(question.member.displayName.eq(author));

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<QuestionResponse> getQuestionsWithTitle(String keyword, Pageable pageable) {
        List<QuestionResponse> result = getQuestion()
                .where(question.title.contains(keyword))
                .orderBy(question.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(question.id)
                .from(question)
                .where(question.title.contains(keyword));

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }

    @Override
    public Page<QuestionResponse> getUnansweredQuestions(Pageable pageable) {
        List<QuestionResponse> result = getQuestion()
                .where(question.answers.size().eq(0))
                .orderBy(question.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(question.id)
                .from(question)
                .where(question.answers.size().eq(0));

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }

    private QuestionDetailResponse fetchQuestionResponse(Long questionId) {
        return queryFactory
                .select(new QQuestionDetailResponse(
                        asNumber(question.id),
                        question.title,
                        question.content,
                        question.likeCount,
                        question.viewCount,
                        question.selectedAnswer,
                        question.answers.size(),
                        question.createdAt,
                        question.updatedAt,
                        member.displayName,
                        member.reputation))
                .from(question)
                .innerJoin(question.member, member)
                .where(question.id.eq(questionId))
                .fetchOne();
    }

    private Page<AnswerResponse> fetchAnswerResponses(Long questionId, Pageable pageable) {
        List<AnswerResponse> result = fetchAnswerIds(questionId, pageable).stream()
                .map(this::fetchAnswerResponse)
                .collect(Collectors.toList());

        JPAQuery<Long> countQuery = queryFactory
                .select(answer.count())
                .from(answer)
                .where(answer.question.id.eq(questionId));

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchOne);
    }

    private AnswerResponse fetchAnswerResponse(Long answerId) {
        return queryFactory
                .select(new QAnswerResponse(
                        asNumber(answer.question.id),
                        asNumber(answer.id),
                        answer.content,
                        answer.selected,
                        answer.createdAt,
                        answer.updatedAt,
                        member.displayName,
                        member.reputation))
                .from(answer)
                .innerJoin(answer.member, member)
                .where(answer.id.eq(answerId))
                .fetchOne();
    }

    private List<Long> fetchAnswerIds(Long questionId, Pageable pageable) {
        return queryFactory
                .select(answer.id)
                .from(answer)
                .where(answer.question.id.eq(questionId))
                .orderBy(answer.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private JPAQuery<QuestionResponse> getQuestion() {
        return queryFactory
                .select(new QQuestionResponse(
                        asNumber(question.id),
                        question.title,
                        question.content,
                        question.likeCount,
                        question.answers.size(),
                        question.selectedAnswer,
                        question.viewCount,
                        question.createdAt,
                        question.updatedAt,
                        question.member.displayName))
                .from(question)
                .innerJoin(question.member, member);
    }
}
