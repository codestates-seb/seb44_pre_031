package com.codestates.pre_project.module.tag.controller;

import com.codestates.pre_project.module.response.Response;
import com.codestates.pre_project.module.tag.dto.response.TagResponse;
import com.codestates.pre_project.module.tag.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "Tag Controller", tags = "Tag")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {
    private final TagService tagService;

    @ApiOperation(value = "태그 조회", notes = "태그 조회")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response getTags() {
        List<TagResponse> responses = tagService.getTags();

        return Response.success(responses);
    }
}
