package com.codestates.pre_project.global.auth.filter;

import com.codestates.pre_project.global.auth.jwt.JwtTokenizer;
import com.codestates.pre_project.global.auth.utils.CustomAuthentication;
import com.codestates.pre_project.global.auth.utils.CustomAuthorityUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;

    public JwtVerificationFilter(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, Object> claims = verifyJws(request);   // JWT 검증
        setAuthenticationToContext(claims);

        filterChain.doFilter(request,response); // 다음 필터 호출
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String authorization = request.getHeader("Authorization");

        return authorization == null || !authorization.startsWith("Bearer"); // 올바른 토큰이 아니기 때문에 해당 filter의 동작을 수행하지 않음
    }

    // JWT 검증
    private Map<String, Object> verifyJws(HttpServletRequest request) {
        String jws = request.getHeader("Authorization").replace("Bearer ","");
        String base64EncodedSecretKey = jwtTokenizer.encodeBase64SecretKey(jwtTokenizer.getSecretKey());
        Map<String, Object> claims = jwtTokenizer.getClaims(jws, base64EncodedSecretKey).getBody();

        return claims;
    }

    // Authentication 객체를 SecurityContext에 저장
    private void setAuthenticationToContext(Map<String,Object> claims) {
        String username = (String) claims.get("username");
        List<GrantedAuthority> authorities = authorityUtils.createAuthorities((List)claims.get("roles"));
//        Long memberId = claims.get("memberId");
        Object memberId = claims.get("memberId");
        Authentication authentication = new UsernamePasswordAuthenticationToken(username,null,authorities);
        CustomAuthentication customAuthentication = new CustomAuthentication(authentication,memberId);
        SecurityContextHolder.getContext().setAuthentication(customAuthentication);
    }
}
