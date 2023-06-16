package com.codestates.pre_project.global.auth.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class CustomAuthentication extends UsernamePasswordAuthenticationToken {
    private final Object memberId;

    public CustomAuthentication(Authentication authentication, Object memberId) {
        super(authentication.getPrincipal(),authentication.getCredentials(),authentication.getAuthorities());
        this.memberId = memberId;
    }

    public Object getMemberId() {
        return memberId;
    }
}
