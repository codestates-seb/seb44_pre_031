package com.codestates.pre_project.module.member.controller;

import com.codestates.pre_project.module.member.dto.MemberDto;
import com.codestates.pre_project.module.member.dto.SignUpDto;
import com.codestates.pre_project.module.member.dto.UpdateMemberDto;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.service.BookmarkService;
import com.codestates.pre_project.module.member.service.MemberService;
import com.codestates.pre_project.module.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import static com.codestates.pre_project.module.member.dto.UpdateMemberDto.toDto;
import static org.springframework.http.HttpStatus.*;

@Api(value = "Member Controller", tags = "Member")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class MemberController {

    private final MemberService memberService;
    private final BookmarkService bookmarkService;

    @ApiOperation(value = "회원가입", notes = "회원 가입을 한다.")
    @PostMapping("/sign-up")
    @ResponseStatus(CREATED)
    public Response signUp(@Valid @RequestBody SignUpDto request) {
        memberService.signUp(SignUpDto.toEntity(request));

        return Response.success();
    }

    @ApiOperation(value = "회원가입", notes = "회원 가입을 한다.")
    @GetMapping("/emails")
    @ResponseStatus(OK)
    public Response sendMessage(@RequestParam("email") @Valid @Email String email) {
        memberService.sendCodeToEmail(email);

        return Response.success();
    }

    @ApiOperation(value = "개별 회원 조회", notes = "개별 회원을 조회한다.")
    @GetMapping("/{user-id}")
    @ResponseStatus(OK)
    public Response findMember(@PathVariable("user-id") Long memberId) {
        Member member = memberService.findMember(memberId);

        return Response.success(MemberDto.of(member));
    }

    @ApiOperation(value = "질문 북마크", notes = "질문에 북마크를 한다.")
    @PostMapping("/{question-id}")
    @ResponseStatus(OK)
    public Response bookmarkQuestion(@PathVariable(name = "question-id") Long questionId) {
        bookmarkService.bookmarkQuestion(questionId);

        return Response.success();
    }

    @ApiOperation(value = "북마크 조회", notes = "북마크한 질문들을 조회한다.")
    @GetMapping("/bookmarks/{user-id}")
    @ResponseStatus(OK)
    public Response findBookmarks(@PathVariable("user-id") Long memberId) {
        return Response.success(memberService.findBookmarks(memberId));
    }

    @ApiOperation(value = "회원 정보 수정", notes = "회원의 정보를 수정한다.")
    @PatchMapping("/{user-id}")
    @ResponseStatus(OK)
    public Response updateMemberInfo(@PathVariable("user-id") Long memberId,
                                     @Valid @RequestBody UpdateMemberDto request) {
        Member member = memberService.updateMemberInfo(memberId, request);

        return Response.success(toDto(member));
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원을 탈퇴한다.")
    @DeleteMapping("/{user-id}")
    @ResponseStatus(NO_CONTENT)
    public Response deleteMember(@PathVariable("user-id") Long memberId) {
        memberService.deleteMember(memberId);

        return Response.success();
    }
}
