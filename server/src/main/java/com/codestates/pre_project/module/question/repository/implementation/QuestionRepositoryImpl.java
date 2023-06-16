package com.codestates.pre_project.module.question.repository.implementation;

import com.codestates.pre_project.module.answer.dto.AnswerResponse;
import com.codestates.pre_project.module.answer.dto.QAnswerResponse;
import com.codestates.pre_project.module.question.dto.response.GetQuestionResponse;
import com.codestates.pre_project.module.question.dto.response.QQuestionDetailResponse;
import com.codestates.pre_project.module.question.dto.response.QuestionDetailResponse;
import com.codestates.pre_project.module.question.repository.QuestionRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

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

    private QuestionDetailResponse fetchQuestionResponse(Long questionId) {
        return queryFactory
                .select(new QQuestionDetailResponse(
                        asNumber(questionId),
                        question.title,
                        question.content,
                        question.voteCount,
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
                        answer.createdAt,
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
