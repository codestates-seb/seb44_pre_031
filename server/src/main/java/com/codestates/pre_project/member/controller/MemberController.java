package com.codestates.pre_project.member.controller;

import com.codestates.pre_project.member.dto.MemberDto;
import com.codestates.pre_project.member.dto.SignUpDto;
import com.codestates.pre_project.member.dto.UpdateMemberDto;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.service.MemberService;
import com.codestates.pre_project.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.codestates.pre_project.member.dto.SignUpDto.SignUpRequest;
import static com.codestates.pre_project.member.dto.SignUpDto.toEntity;
import static com.codestates.pre_project.member.dto.UpdateMemberDto.toDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/users/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@Valid @RequestBody SignUpDto.SignUpRequest request) {
        memberService.signUp(SignUpDto.toEntity(request));
        return Response.success();
    }

    @GetMapping("/users/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response findMember(@PathVariable Long memberId) {
        Member member = memberService.findMember(memberId);
        return Response.success(MemberDto.of(member));
    }

    @PatchMapping("/users/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateMemberInfo(@PathVariable Long memberId,
                                     @Valid @RequestBody UpdateMemberDto request) {
        Member member = memberService.updateMemberInfo(memberId, UpdateMemberDto.toEntity(request));
        return Response.success(toDto(member));
    }

}
