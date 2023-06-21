package com.codestates.pre_project.global.auth.handler;

import com.codestates.pre_project.global.auth.utils.ErrorResponder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MemberAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        log.error("# Authentication failed: {}", exception.getClass().getSimpleName()); // 인증 실패 후, 로그 기록
        log.error("# Stack trace:", exception); // Stack Trace
        ErrorResponder.sendErrorResponse(response);
    }
}


