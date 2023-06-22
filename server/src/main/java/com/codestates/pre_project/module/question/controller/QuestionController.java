package com.codestates.pre_project.module.question.controller;

import com.codestates.pre_project.global.auth.utils.MemberIdExtractor;
import com.codestates.pre_project.module.question.dto.request.QuestionRequest;
import com.codestates.pre_project.module.question.dto.response.GetQuestionResponse;
import com.codestates.pre_project.module.question.dto.response.QuestionResponse;
import com.codestates.pre_project.module.question.service.QuestionLikeService;
import com.codestates.pre_project.module.question.service.QuestionService;
import com.codestates.pre_project.module.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionLikeService questionLikeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createQuestion(@Valid @RequestBody QuestionRequest request) {
        Long memberId = MemberIdExtractor.extractMemberId();
        Long questionId = questionService.createQuestion(memberId, request.toEntity(), request.tags);

        return Response.success(questionId);
    }

    @PatchMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateQuestion(@PathVariable("question-id") Long questionId,
                                   @Valid @RequestBody QuestionRequest request) {
        Long memberId = MemberIdExtractor.extractMemberId();
        questionService.updateQuestion(questionId, memberId, request.toEntity());

        return Response.success();
    }

    @GetMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestion(@PathVariable("question-id") Long questionId,
                                @PageableDefault(size = 30) Pageable pageable) {
        GetQuestionResponse response = questionService.getQuestion(questionId, pageable);

        return Response.success(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestions(@PageableDefault(size = 30) Pageable pageable) {
        Page<QuestionResponse> questions = questionService.getQuestions(pageable);

        return Response.success(questions);
    }

    @GetMapping("/search-keyword/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestionsWithKeyword(@PathVariable("keyword") String keyword,
                                            @PageableDefault(size = 30) Pageable pageable) {
        Page<QuestionResponse> questions = questionService.getQuestionsWithKeyword(keyword, pageable);

        return Response.success(questions);
    }

    @GetMapping("/search-author/{author}")
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestionsWithAuthor(@PathVariable("author") String author,
                                           @PageableDefault(size = 30) Pageable pageable) {
        Page<QuestionResponse> questions = questionService.getQuestionsWithAuthor(author, pageable);

        return Response.success(questions);
    }

    @GetMapping("/search-unanswered")
    @ResponseStatus(HttpStatus.OK)
    public Response getUnansweredQuestion(@PageableDefault(size = 30) Pageable pageable) {
        Page<QuestionResponse> questions = questionService.getUnansweredQuestions(pageable);

        return Response.success(questions);
    }

    // TODO : tag 검색 오류 수정
    @GetMapping("/search-tag/{tag}")
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestionsWithTag(@PathVariable("tag") String tag,
                                       @PageableDefault(size = 30) Pageable pageable) {
        Page<QuestionResponse> questions = questionService.getQuestionsWithTag(tag, pageable);

        return Response.success(questions);
    }

    @DeleteMapping("/{question-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response deleteQuestion(@PathVariable("question-id") Long questionId) {
        Long memberId = MemberIdExtractor.extractMemberId();
        questionService.deleteQuestion(questionId, memberId);

        return Response.success();
    }

    @PostMapping("/{question-id}/{type}/like")
    public Response likeQuestion(@PathVariable("question-id") Long questionId,
                                 @PathVariable("type") int type) {
        questionLikeService.likeQuestion(questionId, type);

        return Response.success();
    }
}
