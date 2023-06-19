package com.codestates.pre_project.module.question.service;

import com.codestates.pre_project.global.auth.utils.MemberIdExtractor;
import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.repository.MemberRepository;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.repository.QuestionRepository;
import com.codestates.pre_project.module.vote.entity.Vote;
import com.codestates.pre_project.module.vote.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.pre_project.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class QuestionLikeService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final VoteRepository voteRepository;

    @Transactional
    public void likeQuestion(Long questionId, int type) {
        Question question = findQuestionById(questionId);
        Long memberId = MemberIdExtractor.extractMemberId();
        Member member = findMemberById(memberId);

        if (voteRepository.existsVoteByQuestionIdAndMemberIdAndVoteType(questionId, memberId, type)) {
            throw new CustomException(ALREADY_VOTES);
        }

        if (type == 1) {
            deleteIfAlreadyVotesOther(question, member, 2);
            question.like();
            voteRepository.save(Vote.of(question, member, 1));
        } else {
            deleteIfAlreadyVotesOther(question, member, 1);
            question.dislike();
            voteRepository.save(Vote.of(question, member, 2));
        }
    }

//    @Transactional
//    public void dislikeQuestion(Long questionId) {
//        Question question = findQuestionById(questionId);
//        Long memberId = MemberIdExtractor.extractMemberId();
//        Member member = findMemberById(memberId);
//    }

    public Question findQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException(QUESTION_NOT_FOUND));
    }

    public Member findMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }

    private void deleteIfAlreadyVotesOther(Question question, Member member, int type) {
        if (voteRepository.findVoteTypeByQuestionAndMember(question.getId(), member.getId(), type).isPresent()) {
            voteRepository.delete(voteRepository.findByQuestionAndMember(question, member));
            if (type == 1) question.dislike();
            else question.like();
        }
    }
}