package com.codestates.pre_project.module.question.controller;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.repository.MemberRepository;
import com.codestates.pre_project.module.question.dto.request.QuestionRequest;
import com.codestates.pre_project.module.question.dto.response.GetQuestionResponse;
import com.codestates.pre_project.module.question.dto.response.QuestionResponse;
import com.codestates.pre_project.module.question.service.QuestionService;
import com.codestates.pre_project.module.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import static com.codestates.pre_project.global.exception.ErrorCode.MEMBER_NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createQuestion(@Valid @RequestBody QuestionRequest request) {
        // TODO: memberId 가져오는 로직 추가
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Object> principal = (HashMap<String, Object>) authentication.getPrincipal();
        Long memberId = Long.valueOf((Integer) principal.get("memberId"));       // memberId 가져오는 로직

        Long questionId = questionService.createQuestion(memberId, request.toEntity());
        return Response.success(questionId);
    }

    @PatchMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateQuestion(@PathVariable("question-id") Long questionId,
                                   @Valid @RequestBody QuestionRequest request) {
        questionService.updateQuestion(questionId, request.toEntity());

        return Response.success();
    }

    @GetMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestion(@PathVariable("question-id") Long questionId) {
        GetQuestionResponse response = questionService.getQuestion(questionId);

        return Response.success(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestions(@PageableDefault(size = 30) Pageable pageable) {
        Page<QuestionResponse> questions = questionService.getQuestions(pageable);

        return Response.success(questions);
    }

    @DeleteMapping("/{question-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response deleteQuestion(@PathVariable("question-id") Long questionId) {
        questionService.deleteQuestion(questionId);

        return Response.success();
    }

    @PostMapping("/{question-id}/like")
    @ResponseStatus(HttpStatus.OK)
    public Response likeQuestion(@PathVariable("question-id") Long questionId) {
        questionService.likeQuestion(questionId);

        return Response.success();
    }

    @PostMapping("/{question-id}/dislike")
    @ResponseStatus(HttpStatus.OK)
    public Response dislikeQuestion(@PathVariable("question-id") Long questionId) {
        questionService.dislikeQuestion(questionId);

        return Response.success();
    }
}
