package com.codestates.pre_project.module.answer.controller;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.repository.MemberRepository;
import com.codestates.pre_project.member.service.MemberService;
import com.codestates.pre_project.module.answer.dto.request.AnswerRequest;
import com.codestates.pre_project.module.answer.service.AnswerService;
import com.codestates.pre_project.module.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static com.codestates.pre_project.global.exception.ErrorCode.MEMBER_NOT_FOUND;

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
        // TODO : memberId 가져오는 로직 추가
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        answerService.createAnswer(member.getId(), questionId, request.toEntity());

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
