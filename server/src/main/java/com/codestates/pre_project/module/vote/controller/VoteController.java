package com.codestates.pre_project.module.vote.controller;

import com.codestates.pre_project.module.response.Response;
import com.codestates.pre_project.module.vote.dto.VoteRequestDto;
import com.codestates.pre_project.module.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vote")
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response vote(@PathVariable(name = "question-id") Long questionId,
                         @RequestBody VoteRequestDto type
    ) {
        voteService.voteQuestion(questionId, type.isType());
        return Response.success();
    }
}