package com.codestates.pre_project.module.answer.controller;

import com.codestates.pre_project.global.auth.utils.MemberIdExtractor;
import com.codestates.pre_project.module.answer.dto.request.AnswerRequest;
import com.codestates.pre_project.module.answer.service.AnswerLikeService;
import com.codestates.pre_project.module.answer.service.AnswerService;
import com.codestates.pre_project.module.member.repository.MemberRepository;
import com.codestates.pre_project.module.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(value = "Answer Controller", tags = "Answer")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answers")
public class AnswerController {
    private final AnswerService answerService;

    @ApiOperation(value = "답변 작성", notes = "답변을 작성한다.")
    @PostMapping("/{question-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createAnswer(@PathVariable("question-id") Long questionId,
                                 @RequestBody AnswerRequest request) {
        Long memberId = MemberIdExtractor.extractMemberId();
        answerService.createAnswer(memberId, questionId, request.toEntity());

        return Response.success();
    }

    @ApiOperation(value = "답변 수정", notes = "답변을 수정한다.")
    @PatchMapping("/{question-id}/{answer-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateAnswer(@PathVariable("answer-id") Long answerId,
                                 @RequestBody AnswerRequest request) {
        Long memberId = MemberIdExtractor.extractMemberId();
        answerService.updateAnswer(answerId, memberId, request.toEntity());

        return Response.success();
    }

    @ApiOperation(value = "답변 채택", notes = "답변을 채택한다.")
    @PostMapping("/{question-id}/{answer-id}/select")
    @ResponseStatus(HttpStatus.OK)
    public Response selectAnswer(@PathVariable("question-id") Long questionId,
                                 @PathVariable("answer-id") Long answerId) {
        Long memberId = MemberIdExtractor.extractMemberId();
        answerService.selectAnswer(questionId, answerId, memberId);

        return Response.success();
    }

    @ApiOperation(value = "답변 삭제", notes = "답변을 삭제한다.")
    @DeleteMapping("/{question-id}/{answer-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response deleteAnswer(@PathVariable("answer-id") Long answerId) {
        Long memberId = MemberIdExtractor.extractMemberId();
        answerService.deleteAnswer(answerId, memberId);

        return Response.success();
    }
}
