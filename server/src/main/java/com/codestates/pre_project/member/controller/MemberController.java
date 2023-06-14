package com.codestates.pre_project.member.controller;

import com.codestates.pre_project.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    MemberServce memberServce;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@Valid @RequestBody SignUpRequestDto request) {
        memberServce.signUp(request);
        return Response.success();
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signIn(@Valid @RequestBody LoginRequestDto request) {
        return Response.success(LoginResponseDto.toEntity(request));
    }

}
