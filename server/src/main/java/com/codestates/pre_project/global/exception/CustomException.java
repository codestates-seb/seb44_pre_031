package com.codestates.pre_project.global.exception;

import lombok.Getter;

public class CustomException extends RuntimeException{
    @Getter
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
