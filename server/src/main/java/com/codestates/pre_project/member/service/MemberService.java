package com.codestates.pre_project.member.service;

import com.codestates.pre_project.global.auth.utils.CustomAuthorityUtils;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.exception.MemberDisplayNameAlreadyExistsException;
import com.codestates.pre_project.member.exception.MemberEmailAlreadyExistsException;
import com.codestates.pre_project.member.exception.MemberNotFoundException;
import com.codestates.pre_project.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    @Transactional
    public void signUp(Member request) {
        validateSignUp(request);
        List<String> roles = authorityUtils.createRoles(request.getEmail());
        memberRepository.save(Member.builder()
                .email(request.getEmail())
                .displayName(request.getDisplayName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build());
    }

    @Transactional(readOnly = true)
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
    }

    @Transactional
    public Member updateMemberInfo(Long memberId, Member member) {
        Member findMember = validateUpdate(memberId, member);
        return Member.updateMemberInfo(findMember, member);
    }

    private void validateSignUp(Member member) {
        if (memberRepository.existsByEmail(member.getEmail()))
            throw new MemberEmailAlreadyExistsException(member.getEmail());
    }

    private Member validateUpdate(Long memberId, Member member) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(MemberNotFoundException::new);
        memberRepository.findByDisplayName(member.getDisplayName()).ifPresent(alreadyExists -> {
            throw new MemberDisplayNameAlreadyExistsException(member.getDisplayName() + "은 이미 사용중!!!!!!!!!!!!!!");
        });
        return findMember;
    }
}
