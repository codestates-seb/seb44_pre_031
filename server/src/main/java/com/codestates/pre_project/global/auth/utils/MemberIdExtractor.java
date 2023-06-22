package com.codestates.pre_project.global.auth.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;

public class MemberIdExtractor {
    public static Long extractMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, Object> principal = (HashMap<String, Object>) authentication.getPrincipal();
        return Long.valueOf((Integer) principal.get("memberId"));
    }
}
