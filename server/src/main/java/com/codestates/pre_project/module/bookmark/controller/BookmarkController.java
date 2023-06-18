package com.codestates.pre_project.module.bookmark.controller;

import com.codestates.pre_project.module.bookmark.service.BookmarkService;
import com.codestates.pre_project.module.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response bookmarkQuestion(@PathVariable(name = "question-id") Long questionId) {
        bookmarkService.bookmarkQuestion(questionId);
        return Response.success();
    }
}