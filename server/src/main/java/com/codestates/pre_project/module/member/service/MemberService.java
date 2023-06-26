package com.codestates.pre_project.module.member.service;

import com.codestates.pre_project.global.auth.utils.CustomAuthorityUtils;
import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.module.bookmark.repository.BookmarkRepository;
import com.codestates.pre_project.module.member.dto.UpdateMemberDto;
import com.codestates.pre_project.module.member.entity.Email;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.repository.EmailRepository;
import com.codestates.pre_project.module.member.repository.MemberRepository;
import com.codestates.pre_project.module.question.dto.response.BookmarkedQuestionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codestates.pre_project.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BookmarkRepository bookmarkRepository;
    private final EmailRepository emailRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    @Transactional
    public void signUp(Member request) {
        validateEmail(request.getEmail());
        verifiedCode(request.getEmail(), request.getCode());

        List<String> roles = authorityUtils.createRoles(request.getEmail());
        String encode = passwordEncoder.encode(request.getPassword());

        memberRepository.save(Member.builder()
                .email(request.getEmail())
                .code(null)
                .displayName(request.getDisplayName())
                .password(encode)
                .roles(roles)
                .build());
    }

    @Transactional
    public void sendCodeToEmail(String email) {
        validateEmail(email);

        String title = "스택오버플로우 이메일 인증 번호";
        String code = createRandomCode();

        emailService.sendEmail(email, title, code);
        // 임시로 email repository 에 저장.....
        emailRepository.save(new Email(email, code));
    }

    public void verifiedCode(String email, String code) {
        validateEmail(email);

        Email emailEntity = emailRepository.findByEmail(email);
        if (!emailEntity.getCode().equals(code)) {
            throw new CustomException(INTERNAL_SERVER_ERROR);
        } else {
            emailRepository.delete(emailEntity);
        }
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
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()) throw new CustomException(MEMBER_EMAIL_ALREADY_EXISTS);
    }

    private Member validateUpdate(Long memberId, UpdateMemberDto request) {
        Member findMember = findMemberById(memberId);

        if (findMember.getDisplayName().equals(request.getDisplayName())
                || memberRepository.findByDisplayName(request.getDisplayName()).isEmpty()
        ) {
            return findMember;
        } else {
            throw new CustomException(MEMBER_DISPLAY_NAME_ALREADY_EXISTS);
        }
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
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
