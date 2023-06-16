package com.codestates.pre_project.module.bookmark.service;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.global.exception.ErrorCode;
import com.codestates.pre_project.member.repository.MemberRepository;
import com.codestates.pre_project.module.question.repository.QuestionRepository;
import com.codestates.pre_project.module.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class BookmarkService {

    private final QuestionRepository questionRepository;
    private final VoteRepository voteRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void bookmarkQuestion(Long questionId) {
        questionRepository.findById(questionId).orElseThrow(() -> new CustomException(ErrorCode.QUESTION_NOT_FOUND));
    }
}
