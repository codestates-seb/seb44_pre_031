package com.codestates.pre_project.module.member.controller;

import com.codestates.pre_project.module.member.dto.MemberDto;
import com.codestates.pre_project.module.member.dto.SignUpDto;
import com.codestates.pre_project.module.member.dto.UpdateMemberDto;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.service.BookmarkService;
import com.codestates.pre_project.module.member.service.MemberService;
import com.codestates.pre_project.module.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import static com.codestates.pre_project.module.member.dto.UpdateMemberDto.toDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;
    private final BookmarkService bookmarkService;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public Response signUp(@Valid @RequestBody SignUpDto request) {
        memberService.signUp(SignUpDto.toEntity(request));
        return Response.success();
    }

    @PostMapping("/emails")
    public Response sendMessage(@RequestParam("email") @Valid @Email String email) {
        return null;
    }

    @GetMapping("/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response findMember(@PathVariable("user-id") Long memberId) {
        Member member = memberService.findMember(memberId);
        return Response.success(MemberDto.of(member));
    }

    @PostMapping("/{question-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response bookmarkQuestion(@PathVariable(name = "question-id") Long questionId) {
        bookmarkService.bookmarkQuestion(questionId);
        return Response.success();
    }

    @GetMapping("/bookmarks/{user-id}")
    public Response findBookmarks(@PathVariable("user-id") Long memberId) {
        return Response.success(memberService.findBookmarks(memberId));
    }

    @PatchMapping("/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response updateMemberInfo(@PathVariable("user-id") Long memberId,
                                     @Valid @RequestBody UpdateMemberDto request) {
        Member member = memberService.updateMemberInfo(memberId, request);
        return Response.success(toDto(member));
    }

    @DeleteMapping("/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    public Response deleteMember(@PathVariable("user-id") Long memberId) {
        memberService.deleteMember(memberId);
        return Response.success();
    }
}
