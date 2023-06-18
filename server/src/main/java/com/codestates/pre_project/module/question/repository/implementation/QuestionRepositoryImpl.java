package com.codestates.pre_project.module.question.repository.implementation;

import com.codestates.pre_project.module.answer.dto.response.AnswerResponse;
import com.codestates.pre_project.module.answer.dto.response.QAnswerResponse;
import com.codestates.pre_project.module.question.dto.response.*;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.repository.QuestionRepositoryCustom;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.codestates.pre_project.member.entity.QMember.member;
import static com.codestates.pre_project.module.answer.entity.QAnswer.answer;
import static com.codestates.pre_project.module.question.entity.QQuestion.question;
import static com.querydsl.core.types.dsl.Expressions.asNumber;

@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public GetQuestionResponse getQuestionWithAnswer(Long questionId) {
        QuestionDetailResponse questionDetailResponse = fetchQuestionResponse(questionId);
        List<AnswerResponse> answerResponses = fetchAnswerResponses(questionId);

        return new GetQuestionResponse(questionDetailResponse, answerResponses);
    }

    @Override
    public Page<QuestionResponse> getQuestions(Pageable pageable) {
        List<QuestionResponse> result = queryFactory
                .select(new QQuestionResponse(
                        asNumber(question.id),
                        question.title,
                        question.content,
                        question.likeCount,
                        // TODO : 답변 갯수 필드 추가 or 다른 방법으로 가져오기
                        question.answers.size(),
                        question.selectedAnswer,
                        question.viewCount,
                        question.createdAt,
                        question.updatedAt,
                        question.member.displayName))
                .from(question)
                .innerJoin(question.member, member)
                .orderBy(question.createdAt.desc())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Question> countQuery = queryFactory
                .selectFrom(question);

        return PageableExecutionUtils.getPage(result, pageable, countQuery::fetchCount);
    }

    private QuestionDetailResponse fetchQuestionResponse(Long questionId) {
        return queryFactory
                .select(new QQuestionDetailResponse(
                        asNumber(questionId),
                        question.title,
                        question.content,
                        question.likeCount,
                        question.selectedAnswer,
                        question.createdAt,
                        member.displayName,
                        member.reputation))
                .from(question)
                .innerJoin(question.member, member)
                .where(question.id.eq(questionId))
                .fetchOne();
    }

    private List<AnswerResponse> fetchAnswerResponses(Long questionId) {
        List<Long> answerIds = fetchAnswerIds(questionId);
        return answerIds.stream()
                .map(this::fetchAnswerResponse)
                .collect(Collectors.toList());
    }

    private AnswerResponse fetchAnswerResponse(Long answerId) {
        return queryFactory
                .select(new QAnswerResponse(
                        answer.id,
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

    private List<Long> fetchAnswerIds(Long questionId) {
        return queryFactory
                .select(answer.id)
                .from(answer)
                .where(answer.question.id.eq(questionId))
                .fetch();
    }
}
