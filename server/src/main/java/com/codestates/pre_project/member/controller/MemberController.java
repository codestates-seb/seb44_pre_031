package com.codestates.pre_project.member.controller;

import com.codestates.pre_project.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.codestates.pre_project.member.dto.SignInDto.SignInRequest;
import static com.codestates.pre_project.member.dto.SignInDto.toEntity;
import static com.codestates.pre_project.member.dto.SignInDto.toResponse;
import static com.codestates.pre_project.member.dto.SignUpDto.SignUpRequest;
import static com.codestates.pre_project.member.dto.SignUpDto.toEntity;



@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    MemberServce memberServce;

    @PostMapping("/users/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@Valid @RequestBody SignUpRequest request) {
        memberServce.signUP(toEntity(request));
        return Response.success();
    }

    @PostMapping("/users/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signIn(@Valid @RequestBody SignInRequest request) {
        memberServce.signIn(toEntity(request));
        return Response.success(toResponse(memberServce.signIn(toEntity(request))));
    }

}
