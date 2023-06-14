package com.codestates.pre_project.member.controller;

import com.codestates.pre_project.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    String body;
    
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response test() {
        return Response.success();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Response test2() {
        return Response.success(body);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Response test3() {
        return Response.failure(404, body);
    }
}
