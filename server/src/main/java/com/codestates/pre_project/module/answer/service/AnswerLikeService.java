package com.codestates.pre_project.module.answer.service;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.module.answer.entity.Answer;
import com.codestates.pre_project.module.answer.entity.AnswerLike;
import com.codestates.pre_project.module.answer.repository.AnswerLikeRepository;
import com.codestates.pre_project.module.answer.repository.AnswerRepository;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.pre_project.global.exception.ErrorCode.*;
import static com.codestates.pre_project.module.answer.entity.enums.VoteStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AnswerLikeService {
    private final AnswerLikeRepository answerLikeRepository;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    public void likeAnswer(Long answerId, Long memberId) {
        AnswerLike answerLike = findAnswerLike(answerId, memberId);
        if (answerLike.getVoteStatus().equals(LIKE)) {
            answerLike.setVoteStatus(NONE);
        } else {
            answerLike.setVoteStatus(LIKE);
        }
    }

    public void dislikeAnswer(Long answerId, Long memberId) {
        AnswerLike answerLike = findAnswerLike(answerId, memberId);
        if (answerLike.getVoteStatus().equals(DISLIKE)) {
            answerLike.setVoteStatus(NONE);
        } else {
            answerLike.setVoteStatus(DISLIKE);
        }
    }

    public Long calculateTotalVoted(Long answerId) {
        Long likeCount = answerLikeRepository.countByAnswerIdAndVoteStatus(answerId, LIKE);
        Long dislikeCount = answerLikeRepository.countByAnswerIdAndVoteStatus(answerId, DISLIKE);

        return likeCount - dislikeCount;
    }

    private AnswerLike findAnswerLike(Long answerId, Long memberId) {

        return answerLikeRepository.findByAnswerIdAndMemberId(answerId, memberId)
                .orElseGet(() -> saveAnswerLike(answerId, memberId));
    }

    public AnswerLike saveAnswerLike(Long answerId, Long memberId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new CustomException(ANSWER_NOT_FOUND));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        AnswerLike answerLike = AnswerLike.builder()
                .answer(answer)
                .member(member)
                .build();

        return answerLikeRepository.save(answerLike);
    }
}
