package com.codestates.pre_project.module.answer.controller;

import com.codestates.pre_project.module.answer.dto.request.AnswerRequest;
import com.codestates.pre_project.module.answer.service.AnswerService;
import com.codestates.pre_project.module.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answers")
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/{question-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Response createAnswer(@PathVariable("question-id") Long questionId,
                                 @RequestBody AnswerRequest request) {
        // TODO : memberId 가져오는 로직 추가
        answerService.createAnswer(memberId, questionId, request.toEntity());

        return Response.success();
    }

    @PatchMapping("/{question-id}/{answer-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateAnswer(@PathVariable("answer-id") Long answerId,
                                 @RequestBody AnswerRequest request) {
        answerService.updateAnswer(answerId, request.toEntity());

        return Response.success();
    }

    @PostMapping("/{question-id}/{answer-id}/select")
    @ResponseStatus(HttpStatus.OK)
    public Response selectAnswer(@PathVariable("question-id") Long questionId,
                                 @PathVariable("answer-id") Long answerId) {
        answerService.selectAnswer(questionId, answerId);

        return Response.success();
    }

    @DeleteMapping("/{question-id}/{answer-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response deleteAnswer(@PathVariable("answer-id") Long answerId) {
        answerService.deleteAnswer(answerId);

        return Response.success();
    }
}
