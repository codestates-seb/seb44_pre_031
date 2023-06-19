package com.codestates.pre_project.module.member.service;

import com.codestates.pre_project.global.auth.utils.CustomAuthorityUtils;
import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.module.member.dto.UpdateMemberDto;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.exception.MemberDisplayNameAlreadyExistsException;
import com.codestates.pre_project.module.member.exception.MemberEmailAlreadyExistsException;
import com.codestates.pre_project.module.member.repository.MemberRepository;
import com.codestates.pre_project.module.bookmark.repository.BookmarkRepository;
import com.codestates.pre_project.module.question.dto.response.BookmarkedQuestionsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.codestates.pre_project.global.exception.ErrorCode.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;
    private final BookmarkRepository bookmarkRepository;

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
        Member member = findMemberById(memberId);
        memberRepository.delete(member);
    }

    private void validateSignUp(Member member) {
        if (memberRepository.existsByEmail(member.getEmail()))
            throw new MemberEmailAlreadyExistsException(member.getEmail());
    }

    private Member validateUpdate(Long memberId, UpdateMemberDto request) {
        Member findMember = findMemberById(memberId);
        memberRepository.findByDisplayName(request.getDisplayName()).ifPresent(alreadyExists -> {
            throw new MemberDisplayNameAlreadyExistsException(request.getDisplayName() + "은 이미 사용중!!!!!!!!!!!!!!");
        });
        return findMember;
    }

    private Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }
}
