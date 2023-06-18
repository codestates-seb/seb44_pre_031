package com.codestates.pre_project.module.bookmark.service;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.global.exception.ErrorCode;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.repository.MemberRepository;
import com.codestates.pre_project.module.bookmark.entity.Bookmark;
import com.codestates.pre_project.module.bookmark.repository.BookmarkRepository;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.repository.QuestionRepository;
import com.codestates.pre_project.module.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;

import static com.codestates.pre_project.global.exception.ErrorCode.*;


@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final QuestionRepository questionRepository;
    private final BookmarkRepository bookmarkRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void bookmarkQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new CustomException(QUESTION_NOT_FOUND));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        if (bookmarkRepository.findByQuestionAndMember(question, member) == null) {
            question.bookmark();
            Bookmark bookmark = new Bookmark(question, member);
            bookmarkRepository.save(bookmark);
        } else {
            Bookmark byQuestionAndMember = bookmarkRepository.findByQuestionAndMember(question, member);
            byQuestionAndMember.cancelBookmark(question);
            bookmarkRepository.delete(byQuestionAndMember);
        }
    }
}
