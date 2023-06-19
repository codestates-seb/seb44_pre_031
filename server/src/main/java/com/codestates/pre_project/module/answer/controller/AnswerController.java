package com.codestates.pre_project.module.answer.controller;

import com.codestates.pre_project.global.auth.utils.MemberIdExtractor;
import com.codestates.pre_project.module.answer.dto.request.AnswerRequest;
import com.codestates.pre_project.module.answer.service.AnswerService;
import com.codestates.pre_project.module.member.repository.MemberRepository;
import com.codestates.pre_project.module.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answers")
public class AnswerController {
    private final AnswerService answerService;
    private final MemberRepository memberRepository;

    @PostMapping("/{question-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createAnswer(@PathVariable("question-id") Long questionId,
                                 @RequestBody AnswerRequest request) {
        Long memberId = MemberIdExtractor.extractMemberId();
        answerService.createAnswer(memberId, questionId, request.toEntity());

        return Response.success();
    }

    @PatchMapping("/{question-id}/{answer-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateAnswer(@PathVariable("answer-id") Long answerId,
                                 @RequestBody AnswerRequest request) {
        Long memberId = MemberIdExtractor.extractMemberId();
        answerService.updateAnswer(answerId, memberId, request.toEntity());

        return Response.success();
    }

    @PostMapping("/{question-id}/{answer-id}/select")
    @ResponseStatus(HttpStatus.OK)
    public Response selectAnswer(@PathVariable("question-id") Long questionId,
                                 @PathVariable("answer-id") Long answerId) {
        Long memberId = MemberIdExtractor.extractMemberId();
        answerService.selectAnswer(questionId, answerId, memberId);

        return Response.success();
    }

    @DeleteMapping("/{question-id}/{answer-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response deleteAnswer(@PathVariable("answer-id") Long answerId) {
        Long memberId = MemberIdExtractor.extractMemberId();
        answerService.deleteAnswer(answerId, memberId);

        return Response.success();
    }
}
