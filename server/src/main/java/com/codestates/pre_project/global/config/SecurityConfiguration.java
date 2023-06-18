package com.codestates.pre_project.global.config;

import com.codestates.pre_project.global.auth.filter.JwtAuthenticationFilter;
import com.codestates.pre_project.global.auth.filter.JwtVerificationFilter;
import com.codestates.pre_project.global.auth.handler.MemberAuthenticationFailureHandler;
import com.codestates.pre_project.global.auth.handler.MemberAuthenticationSuccessHandler;
import com.codestates.pre_project.global.auth.jwt.JwtTokenizer;
import com.codestates.pre_project.global.auth.utils.CustomAuthorityUtils;
import com.codestates.pre_project.member.repository.MemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
public class SecurityConfiguration {
    private final JwtTokenizer jwtTokenizer;
    private final CustomAuthorityUtils authorityUtils;


    public SecurityConfiguration(JwtTokenizer jwtTokenizer, CustomAuthorityUtils authorityUtils) {
        this.jwtTokenizer = jwtTokenizer;
        this.authorityUtils = authorityUtils;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          http
                  .headers().frameOptions().sameOrigin() // 동일 출처로부터 들어오는 요청만 허용, H2 사용 위함
                  .and()
                  .csrf().disable()  // 로컬 환경에서 테스트하기 위함
                  .cors(withDefaults()) // CORS 설정 추가, 프론트엔드와 통신
                  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 생성하지 않음
                  .and()
                  .formLogin().disable() // SSR 방식이 아닌 CSR 방식
                  .httpBasic().disable()
                  .apply(new CustomFilterConfigurer()) // 구현한 filter 등록 역할
                  .and()
                  .authorizeHttpRequests(authorize -> authorize
                          .antMatchers(HttpMethod.POST, "/*/sign-up").permitAll()
                          .antMatchers(HttpMethod.POST,"/*/sign-in").permitAll()
                          .antMatchers(HttpMethod.PATCH, "/*/users/**").hasRole("USER")
                          .antMatchers(HttpMethod.GET, "/*/users/**").hasRole("USER")
                          .antMatchers(HttpMethod.GET, "/*/users/**").hasRole("ADMIN")
                          .antMatchers(HttpMethod.DELETE, "/*/users/**").hasRole("USER")
                          .anyRequest().permitAll()); // 멤버 관련 접근 권한 부여

        return http.build();
    }

    // 구현한 filter 등록 역할
    public class CustomFilterConfigurer extends AbstractHttpConfigurer<CustomFilterConfigurer, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtTokenizer);
            jwtAuthenticationFilter.setFilterProcessesUrl("/api/users/sign-in"); // request URL 등록
            jwtAuthenticationFilter.setAuthenticationSuccessHandler(new MemberAuthenticationSuccessHandler()); // 인증 성공시
            jwtAuthenticationFilter.setAuthenticationFailureHandler(new MemberAuthenticationFailureHandler()); // 인증 실패시

            JwtVerificationFilter jwtVerificationFilter = new JwtVerificationFilter(jwtTokenizer,authorityUtils); // Jwt 검증 필터
            builder
                    .addFilter(jwtAuthenticationFilter)
                    .addFilterAfter(jwtVerificationFilter, JwtAuthenticationFilter.class);

        }
    }


    // PasswordEncoder Bean 객체 생성
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // CORS 정책 설정
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // 모든 출처에 대한 통신 허용
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PATCH","DELETE")); // HTTP Method에 대한 통신 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 URL에 CORS 정책 적용
        return source;
    }

}
