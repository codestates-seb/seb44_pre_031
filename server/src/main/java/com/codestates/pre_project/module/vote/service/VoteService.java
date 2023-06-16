package com.codestates.pre_project.module.vote.service;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.member.repository.MemberRepository;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.repository.QuestionRepository;
import com.codestates.pre_project.module.vote.entity.Vote;
import com.codestates.pre_project.module.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.pre_project.global.exception.ErrorCode.MEMBER_NOT_FOUND;
import static com.codestates.pre_project.global.exception.ErrorCode.QUESTION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final QuestionRepository questionRepository;
    private final VoteRepository voteRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void voteQuestion(Long questionId) {
//        Question question = questionRepository.findById(questionId).orElseThrow(() -> new CustomException(QUESTION_NOT_FOUND));
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Member member = memberRepository.findByEmail(authentication.getName()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
//
//        if (voteRepository.findByQuestionAndMember(question, member) == null) {
//            question.setVoteCount(question.getVoteCount() + 1);
//            Vote vote = Vote.like(question, member);
//            voteRepository.save(vote);
//        } else {
//            Vote findVote = voteRepository.findByQuestionAndMember(question, member);
//            Vote dislike = findVote.dislike(question, member);
//            voteRepository.delete(dislike);
//        }
    }
}
