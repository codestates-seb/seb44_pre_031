package com.codestates.pre_project.module.question.repository.implementation;

import com.codestates.pre_project.module.answer.dto.response.AnswerResponse;
import com.codestates.pre_project.module.answer.dto.response.QAnswerResponse;
import com.codestates.pre_project.module.question.dto.response.*;
import com.codestates.pre_project.module.question.repository.QuestionRepositoryCustom;
import com.codestates.pre_project.module.tag.dto.response.QTagResponse;
import com.codestates.pre_project.module.tag.dto.response.TagResponse;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.codestates.pre_project.module.answer.entity.QAnswer.answer;
import static com.codestates.pre_project.module.member.entity.QMember.member;
import static com.codestates.pre_project.module.question.entity.QQuestion.question;
import static com.codestates.pre_project.module.question.entity.QQuestionTag.questionTag;
import static com.codestates.pre_project.module.tag.entity.QTag.tag;
import static com.querydsl.core.types.dsl.Expressions.asNumber;

@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    // 질문 상세 페이지 response
    @Override
    public GetQuestionDetailResponse getQuestionDetail(Long questionId, Pageable pageable) {
        QuestionDetailResponse questionDetailResponse = fetchQuestionDetailResponse(questionId);
        Page<AnswerResponse> answerResponses = fetchAnswerResponses(questionId, pageable);
        List<TagResponse> tagResponses = getTagResponses(questionId);

        return new GetQuestionDetailResponse(questionDetailResponse, answerResponses, tagResponses);
    }

    // 질문 개별 response (질문 + 태그들)
    @Override
    public GetQuestionsResponse getQuestionAndTags(Long questionId) {
        QuestionResponse question = fetchQuestionResponse(questionId);
        List<TagResponse> tags = getTagResponses(questionId);

        return new GetQuestionsResponse(question, tags);
    }

    // 질문 전체 페이지 response
    @Override
    public Page<GetQuestionsResponse> getQuestions(Pageable pageable) {
        List<Long> questionIds = fetchAllQuestionIds().fetch();
        List<GetQuestionsResponse> result = questionIds.stream()
                .map(this::getQuestionAndTags)
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, questionIds.size());
    }

    // 모든 질문 id 가져오기
    private JPAQuery<Long> fetchAllQuestionIds() {
        return queryFactory
                .select(question.id)
                .from(question)
                .orderBy(question.createdAt.desc());
    }

    // 태그 전체 response
    private List<TagResponse> getTagResponses(Long questionId) {
        return fetchTagIds(questionId).stream()
                .map(this::fetchTagResponse)
                .collect(Collectors.toList());
    }

    // 개별 태그 response
    private TagResponse fetchTagResponse(Long tagId) {
        return queryFactory
                .select(new QTagResponse(
                        tag.name,
                        question.count(),
                        tag.createdAt))
                .from(tag)
                .leftJoin(questionTag).on(questionTag.tag.id.eq(tagId))
                .leftJoin(question).on(question.eq(questionTag.question))
                .fetchOne();
    }

    // 질문 id로 모든 태그 id 가져오기
    private  List<Long> fetchTagIds(Long questionId) {
        return queryFactory
                .select(questionTag.tag.id)
                .from(questionTag)
                .where(questionTag.question.id.eq(questionId))
                .fetch();
    }

    // 작성자 검색
    @Override
    public Page<GetQuestionsResponse> getQuestionsByAuthor(String author, Pageable pageable) {
        List<Long> questionIds = fetchAllQuestionIds()
                .where(member.displayName.eq(author))
                .innerJoin(question.member, member)
                .fetch();

        List<GetQuestionsResponse> result = questionIds.stream()
                .map(this::getQuestionAndTags)
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, questionIds.size());
    }

    // 제목 검색
    @Override
    public Page<GetQuestionsResponse> getQuestionsByTitle(String keyword, Pageable pageable) {
        List<Long> questionIds = fetchAllQuestionIds()
                .where(question.title.contains(keyword))
                .fetch();

        List<GetQuestionsResponse> result = questionIds.stream()
                .map(this::getQuestionAndTags)
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, questionIds.size());
    }

    // 답변 0 질문 검색
    @Override
    public Page<GetQuestionsResponse> getUnansweredQuestions(Pageable pageable) {
        List<Long> questionIds = fetchAllQuestionIds()
                .where(question.answers.size().eq(0))
                .fetch();

        List<GetQuestionsResponse> result = questionIds.stream()
                .map(this::getQuestionAndTags)
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, questionIds.size());
    }

    // 태그 검색
    @Override
    public Page<GetQuestionsResponse> getQuestionsByTag(List<Long> questionIds, Pageable pageable) {
        List<GetQuestionsResponse> result = questionIds.stream()
                .map(this::getQuestionAndTags)
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }

    // 개별 질문 상세 response
    private QuestionDetailResponse fetchQuestionDetailResponse(Long questionId) {
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

    // 개별 답변 response
    private QuestionResponse fetchQuestionResponse(Long questionId) {
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
                        member.displayName))
                .from(question)
                .innerJoin(question.member, member)
                .where(question.id.eq(questionId))
                .fetchOne();
    }

    // 전체 답변 response
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
}
