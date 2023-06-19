package com.codestates.pre_project.global.auth.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.codestates.pre_project.global.exception.ErrorCode;
import com.codestates.pre_project.global.exception.ErrorResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class ErrorResponder {
    public static void sendErrorResponse(HttpServletResponse response) throws IOException {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(ErrorCode.MEMBER_UNAUTHORIZED)
                .message(ErrorCode.MEMBER_UNAUTHORIZED.getMessage())
                .build();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }
}
