package com.codestates.pre_project.member.service;

import com.codestates.pre_project.auth.utils.CustomAuthorityUtils;
import com.codestates.pre_project.member.dto.SignInDto;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.exception.LoginFailureException;
import com.codestates.pre_project.member.exception.MemberEmailAlreadyExistsException;
import com.codestates.pre_project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private PasswordEncoder passwordEncoder;
    private MemberRepository memberRepository;
    private CustomAuthorityUtils authorityUtils;

    @Transactional
    public void signUp(Member request) {
        validateSignUp(request);
        memberRepository.save(Member.builder()
                .email(request.getEmail())
                .displayName(request.getDisplayName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(authorityUtils.createRoles(request.getEmail()))
                .build());
    }

//    @Transactional
//    public Member signIn(Member request) {
//        validateSignUp(request);
//        return Member.builder()
//                .email(request.getEmail())
//                .displayName(request.getDisplayName())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .build();
//    }

    private void validateSignUp(Member member) {
        if (memberRepository.existsByEmail(member.getEmail()))
            throw new MemberEmailAlreadyExistsException(member.getEmail());
    }

    private void validatePassword(SignInDto.SignInRequest request, Member member) {
        if(!passwordEncoder.matches(request.getPassword(), member.getPassword()))
            throw new LoginFailureException();
    }
}