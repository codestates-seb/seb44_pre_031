package com.codestates.pre_project.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // 400
    REQUEST_VALIDATION_FAIL(HttpStatus.BAD_REQUEST, "잘못된 요청 값입니다."),

    // 401
    NONE_AUTHORIZATION_TOKEN(HttpStatus.UNAUTHORIZED, "권한 정보가 없는 토큰입니다."),
    MEMBER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"인증되지 않은 사용자입니다."),

    // 403
    MEMBER_ACCESS_DENIED(HttpStatus.FORBIDDEN,"권한이 없습니다."),

    // 404
    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "질문이 존재하지 않습니다."),
    ANSWER_NOT_FOUND(HttpStatus.NOT_FOUND, "답변이 존재하지 않습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "멤버가 존재하지 않습니다."),
    TAG_NOT_FOUND(HttpStatus.NOT_FOUND, "태그가 존재하지 않습니다."),

    // 409
    ALREADY_SELECTED_ANSWER(HttpStatus.CONFLICT, "이미 채택된 답변이 존재합니다."),
    ALREADY_VOTES(HttpStatus.CONFLICT, "이미 질문에 투표하셨습니다."),
    NOT_SUPPORTED_IMAGE_TYPE(HttpStatus.CONFLICT, "지원하지 않는 이미지 형식입니다."),
    MEMBER_EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, "중복된 이메일 입니다."),
    MEMBER_DISPLAY_NAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "중복된 이름 입니다."),

    // 500
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다. 관리자에게 문의하세요.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
