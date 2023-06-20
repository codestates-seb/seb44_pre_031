package com.codestates.pre_project.module.member.service;

import com.codestates.pre_project.global.auth.utils.CustomAuthorityUtils;
import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.module.bookmark.repository.BookmarkRepository;
import com.codestates.pre_project.module.member.dto.UpdateMemberDto;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.repository.MemberRepository;
import com.codestates.pre_project.module.question.dto.response.BookmarkedQuestionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codestates.pre_project.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final CustomAuthorityUtils authorityUtils;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(Member request) {
        validateEmail(request.getEmail());
        List<String> roles = authorityUtils.createRoles(request.getEmail());
        memberRepository.save(Member.builder()
                .email(request.getEmail())
                .displayName(request.getDisplayName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build());
    }

    @Transactional
    public void sendCodeToEmail(String to) {
        validateEmail(to);
        String title = "스택오버플로우 이메일 인증 번호";
        String code = createRandomCode();

        // 일단 임시방편으로 이메일 인증 코드를 보내면 member 에 업데이트
        emailService.sendEmail(to, title, code);
        Member member = findMemberByEmail(to);
        member.setEmailCode(code);
    }

    @Transactional(readOnly = true)
    public Member findMember(Long memberId) {
        return findMemberById(memberId);
    }

    @Transactional(readOnly = true)
    public List<BookmarkedQuestionsResponse> findBookmarks(Long memberId) {
        Member member = findMemberById(memberId);
        return bookmarkRepository.findAllByMember(member).stream()
                .map(bookmark -> BookmarkedQuestionsResponse.toDto(bookmark.getQuestion()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Member updateMemberInfo(Long memberId, UpdateMemberDto request) {
        Member findMember = validateUpdate(memberId, request);
        return Member.updateMemberInfo(findMember, request);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.delete(findMemberById(memberId));
    }

    private void validateEmail(String email) {
        memberRepository.existsByEmail(email)
                .orElseThrow(() -> new CustomException(MEMBER_EMAIL_ALREADY_EXISTS));
    }

    private Member validateUpdate(Long memberId, UpdateMemberDto request) {
        Member findMember = findMemberById(memberId);
        memberRepository.findByDisplayName(request.getDisplayName()).ifPresent(alreadyExists -> {
            throw new CustomException(MEMBER_DISPLAY_NAME_ALREADY_EXISTS);
        });
        return findMember;
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }

    private String createRandomCode() {
        int length = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new CustomException(INTERNAL_SERVER_ERROR);
        }
    }
}
