package com.codestates.pre_project.module.question.controller;

import com.codestates.pre_project.global.auth.utils.MemberIdExtractor;
import com.codestates.pre_project.module.answer.service.AnswerService;
import com.codestates.pre_project.module.question.dto.request.QuestionRequest;
import com.codestates.pre_project.module.question.dto.response.GetQuestionDetailResponse;
import com.codestates.pre_project.module.question.dto.response.GetQuestionsResponse;
import com.codestates.pre_project.module.question.dto.response.QuestionResponse;
import com.codestates.pre_project.module.question.service.QuestionLikeService;
import com.codestates.pre_project.module.question.service.QuestionService;
import com.codestates.pre_project.module.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Question Controller", tags = "Question")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionLikeService questionLikeService;
    private final AnswerService answerService;

    @ApiOperation(value = "질문 작성", notes = "질문을 작성한다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createQuestion(@Valid @RequestBody QuestionRequest request) {
        Long memberId = MemberIdExtractor.extractMemberId();
        Long questionId = questionService.createQuestion(memberId, request.toEntity(), request.tags);

        return Response.success(questionId);
    }

    @ApiOperation(value = "질문 수정", notes = "질문을 수정한다.")
    @PatchMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateQuestion(@PathVariable("question-id") Long questionId,
                                   @Valid @RequestBody QuestionRequest request) {
        Long memberId = MemberIdExtractor.extractMemberId();
        questionService.updateQuestion(questionId, memberId, request.toEntity());

        return Response.success();
    }

    @ApiOperation(value = "질문 조회", notes = "개별 질문을 조회한다.")
    @GetMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestion(@PathVariable("question-id") Long questionId,
                                @PageableDefault(size = 30) Pageable pageable) {
        answerService.setVoteCount(questionService.findAnswers(questionId));
        GetQuestionDetailResponse response = questionService.getQuestion(questionId, pageable);

        return Response.success(response);
    }

    @ApiOperation(value = "전체 질문 조회", notes = "전체 질문을 조회한다.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestions(@PageableDefault(size = 30) Pageable pageable) {
        Page<GetQuestionsResponse> questions = questionService.getQuestions(pageable);

        return Response.success(questions);
    }

    @ApiOperation(value = "키워드로 검색", notes = "키워드로 검색해 질문을 조회한다.")
    @GetMapping("/search-keyword/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestionsWithKeyword(@PathVariable("keyword") String keyword,
                                            @PageableDefault(size = 30) Pageable pageable) {
        Page<GetQuestionsResponse> questions = questionService.getQuestionsWithKeyword(keyword, pageable);

        return Response.success(questions);
    }

    @ApiOperation(value = "작성자로 검색", notes = "질문 작성자로 검색해 질문을 조회한다.")
    @GetMapping("/search-author/{author}")
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestionsWithAuthor(@PathVariable("author") String author,
                                           @PageableDefault(size = 30) Pageable pageable) {
        Page<GetQuestionsResponse> questions = questionService.getQuestionsWithAuthor(author, pageable);

        return Response.success(questions);
    }

    @ApiOperation(value = "답변이없는 질문 검색", notes = "아직 답변이 없는 질문들을 조회한다.")
    @GetMapping("/search-unanswered")
    @ResponseStatus(HttpStatus.OK)
    public Response getUnansweredQuestion(@PageableDefault(size = 30) Pageable pageable) {
        Page<GetQuestionsResponse> questions = questionService.getUnansweredQuestions(pageable);

        return Response.success(questions);
    }

    @ApiOperation(value = "태그 검색", notes = "태그로 검색해 질문들을 조회한다. ")
    @GetMapping("/search-tag/{tag}")
    @ResponseStatus(HttpStatus.OK)
    public Response getQuestionsWithTag(@PathVariable("tag") String tag,
                                       @PageableDefault(size = 30) Pageable pageable) {
        Page<GetQuestionsResponse> questions = questionService.getQuestionsWithTag(tag, pageable);

        return Response.success(questions);
    }

    @ApiOperation(value = "질문 삭제", notes = "질문을 삭제한다.")
    @DeleteMapping("/{question-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Response deleteQuestion(@PathVariable("question-id") Long questionId) {
        Long memberId = MemberIdExtractor.extractMemberId();
        questionService.deleteQuestion(questionId, memberId);

        return Response.success();
    }

    @ApiOperation(value = "질문 투표", notes = "질문에 투표(좋아요, 싫어요)를 한다.")
    @PostMapping("/{question-id}/{type}/like")
    public Response likeQuestion(@PathVariable("question-id") Long questionId,
                                 @PathVariable("type") int type) {
        questionLikeService.likeQuestion(questionId, type);

        return Response.success();
    }
}
