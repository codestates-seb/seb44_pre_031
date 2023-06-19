package com.codestates.pre_project.global.auth.handler;

import com.codestates.pre_project.global.auth.utils.ErrorResponder;
import com.codestates.pre_project.global.exception.ErrorCode;
import com.codestates.pre_project.global.exception.ErrorResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class MemberAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("Forbidden error happened: {}", accessDeniedException.getMessage());
        ErrorResponder.sendErrorResponse(response);
    }
    private void sendErrorResponse(HttpServletResponse response) throws IOException {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(ErrorCode.MEMBER_ACCESS_DENIED)
                .message(ErrorCode.MEMBER_ACCESS_DENIED.getMessage())
                .build();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }
}
