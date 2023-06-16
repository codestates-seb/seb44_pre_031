package com.codestates.pre_project.global.auth.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class CustomAuthentication extends UsernamePasswordAuthenticationToken {
    private final Long memberId;

    public CustomAuthentication(Authentication authentication, Long memberId) {
        super(authentication.getPrincipal(),authentication.getCredentials(),authentication.getAuthorities());
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
}
