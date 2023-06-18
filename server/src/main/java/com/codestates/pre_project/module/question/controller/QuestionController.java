package com.codestates.pre_project.module.question.controller;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.repository.MemberRepository;
import com.codestates.pre_project.module.question.dto.request.QuestionRequest;
import com.codestates.pre_project.module.question.dto.response.GetQuestionResponse;
import com.codestates.pre_project.module.question.dto.response.QuestionResponse;
import com.codestates.pre_project.module.question.entity.Question;
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
import java.util.List;

import static com.codestates.pre_project.global.exception.ErrorCode.MEMBER_NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final MemberRepository memberRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Response createQuestion(@Valid @RequestBody QuestionRequest request) {
        // TODO: memberId 가져오는 로직 추가
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        Long questionId = questionService.createQuestion(member.getId(), request.toEntity());
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
}
