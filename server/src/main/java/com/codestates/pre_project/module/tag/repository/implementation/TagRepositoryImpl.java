package com.codestates.pre_project.module.tag.repository.implementation;

import com.codestates.pre_project.module.tag.dto.response.QTagResponse;
import com.codestates.pre_project.module.tag.dto.response.TagResponse;
import com.codestates.pre_project.module.tag.repository.TagRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.codestates.pre_project.module.question.entity.QQuestion.question;
import static com.codestates.pre_project.module.question.entity.QQuestionTag.questionTag;
import static com.codestates.pre_project.module.tag.entity.QTag.tag;

@RequiredArgsConstructor
public class TagRepositoryImpl implements TagRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<TagResponse> getAllTagResponses(List<Long> tagIds) {
        return tagIds.stream()
                .map(this::getTagResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> getQuestionIds(Long tagId) {
        return queryFactory
                .select(question.id)
                .from(question)
                .leftJoin(questionTag).on(question.eq(questionTag.question))
                .leftJoin(tag).on(questionTag.tag.id.eq(tagId))
                .fetch();
    }

    private TagResponse getTagResponse(Long tagId) {
        return queryFactory
                .select(new QTagResponse(
                        tag.name,
                        tag.count(),
                        tag.createdAt))
                .from(tag)
                .leftJoin(questionTag).on(questionTag.tag.eq(tag))
                .leftJoin(question).on(question.eq(questionTag.question))
                .where(tag.id.eq(tagId))
                .fetchOne();
    }

}
