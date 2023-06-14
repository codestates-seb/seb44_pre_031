package com.codestates.pre_project.member.controller;

import com.codestates.pre_project.member.service.MemberService;
import com.codestates.pre_project.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.codestates.pre_project.member.dto.SignUpDto.SignUpRequest;
import static com.codestates.pre_project.member.dto.SignUpDto.toEntity;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    MemberService memberService;

    @PostMapping("/users/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@Valid @RequestBody SignUpRequest request) {
        memberService.signUp(toEntity(request));
        return Response.success();
    }

//    @PostMapping("/users/sign-up")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Response signIn(@Valid @RequestBody SignInRequest request) {
//        return Response.success(toResponse(memberService.signIn(toEntity(request))));
//    }

}
