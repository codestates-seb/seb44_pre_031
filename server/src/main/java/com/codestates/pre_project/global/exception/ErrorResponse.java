package com.codestates.pre_project.global.exception;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {
    private ErrorCode errorCode;
    private String message;

    @Builder
    public ErrorResponse(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public static ErrorResponse from(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .message(errorCode.getMessage())
                .build();
    }

    public static ErrorResponse withMessage(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }
}
