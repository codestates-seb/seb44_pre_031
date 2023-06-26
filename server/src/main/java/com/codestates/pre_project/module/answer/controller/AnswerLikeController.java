package com.codestates.pre_project.module.answer.controller;

import com.codestates.pre_project.global.auth.utils.MemberIdExtractor;
import com.codestates.pre_project.module.answer.service.AnswerLikeService;
import com.codestates.pre_project.module.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answers")
public class AnswerLikeController {
    private final AnswerLikeService answerLikeService;

    @PostMapping("/{answer-id}/like")
    public Response likeAnswer(@PathVariable("answer-id") Long answerId) {
        Long memberId = MemberIdExtractor.extractMemberId();
        answerLikeService.likeAnswer(answerId, memberId);
        Long voteCount = answerLikeService.calculateTotalVoted(answerId);

        return Response.success(voteCount);
    }

    @PostMapping("/{answer-id}/dislike")
    public Response dislikeAnswer(@PathVariable("answer-id") Long answerId) {
        Long memberId = MemberIdExtractor.extractMemberId();
        answerLikeService.dislikeAnswer(answerId, memberId);
        Long voteCount = answerLikeService.calculateTotalVoted(answerId);

        return Response.success(voteCount);
    }
}
