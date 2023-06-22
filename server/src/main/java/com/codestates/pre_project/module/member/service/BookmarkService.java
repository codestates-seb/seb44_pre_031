package com.codestates.pre_project.module.member.service;

import com.codestates.pre_project.global.auth.utils.MemberIdExtractor;
import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.repository.MemberRepository;
import com.codestates.pre_project.module.bookmark.entity.Bookmark;
import com.codestates.pre_project.module.bookmark.repository.BookmarkRepository;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.pre_project.global.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final QuestionRepository questionRepository;
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void bookmarkQuestion(Long questionId) {
        Question question = findQuestionById(questionId);
        Long memberId = MemberIdExtractor.extractMemberId();
        Member member = findMemberById(memberId);

        if (!bookmarkRepository.existsBookmarkByQuestionIdAndMemberId(questionId, memberId)) {
            question.bookmark();
            bookmarkRepository.save(Bookmark.of(question, member));
        } else {
            Bookmark byQuestionAndMember = bookmarkRepository.findByQuestionIdAndMemberId(questionId, memberId);
            byQuestionAndMember.cancelBookmark(question);
            bookmarkRepository.delete(byQuestionAndMember);
        }
    }

    public Question findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException(QUESTION_NOT_FOUND));
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }
}
