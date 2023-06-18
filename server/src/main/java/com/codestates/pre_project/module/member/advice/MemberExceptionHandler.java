package com.codestates.pre_project.module.member.advice;

import com.codestates.pre_project.module.member.exception.LoginFailureException;
import com.codestates.pre_project.module.member.exception.MemberEmailAlreadyExistsException;
import com.codestates.pre_project.module.member.exception.MemberNotEqualsException;
import com.codestates.pre_project.module.member.exception.MemberNotFoundException;
import com.codestates.pre_project.module.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice(basePackages = "com.codestates.pre_project.member")
public class MemberExceptionHandler {
    // 401 응답
    // 이메일 혹은 비밀번호 오류시
    @ExceptionHandler(LoginFailureException.class)
    @ResponseStatus(UNAUTHORIZED)
    public Response loginFailureException() {
        return Response.failure(401, "로그인에 실패하였습니다.");
    }

    // 401 응답
    // 유저 정보가 일치하지 않음
    @ExceptionHandler(MemberNotEqualsException.class)
    @ResponseStatus(UNAUTHORIZED)
    public Response memberNotEqualsException() {
        return Response.failure(401, "멤버 정보가 일치하지 않습니다.");
    }

    // 409 응답
    // 이메일 중복
    @ExceptionHandler(MemberEmailAlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public Response memberEmailAlreadyExistsException(MemberEmailAlreadyExistsException e) {
        return Response.failure(409, e.getMessage() + "은 중복된 이메일 입니다.");
    }

    // 404 응답
    // 요청한 자원을 찾을 수 없음
    @ExceptionHandler(MemberNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response memberNotFoundException() {
        return Response.failure(404, "용청한 멤버를 찾을 수 없습니다.");
    }
}
