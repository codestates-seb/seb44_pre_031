package com.codestates.pre_project.module.question.service;

import com.codestates.pre_project.global.auth.utils.MemberIdExtractor;
import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.repository.MemberRepository;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.repository.QuestionRepository;
import com.codestates.pre_project.module.question.entity.QuestionLike;
import com.codestates.pre_project.module.question.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.pre_project.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class QuestionLikeService {

    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final LikeRepository likeRepository;

    @Transactional
    public void likeQuestion(Long questionId, int type) {
        Question question = findQuestionById(questionId);
        Long memberId = MemberIdExtractor.extractMemberId();
        Member member = findMemberById(memberId);

        if (likeRepository.existsQuestionLikeByQuestionIdAndMemberIdAndVoteType(questionId, memberId, type)) {
            throw new CustomException(ALREADY_VOTES);
        }

        if (type == 1) {
            deleteIfAlreadyVotesOther(question, member, 2);
            question.like();
            likeRepository.save(QuestionLike.of(question, member, 1));
        } else {
            deleteIfAlreadyVotesOther(question, member, 1);
            question.dislike();
            likeRepository.save(QuestionLike.of(question, member, 2));
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

    private void deleteIfAlreadyVotesOther(Question question, Member member, int type) {
        if (likeRepository.findVoteTypeByQuestionAndMember(question.getId(), member.getId(), type).isPresent()) {
            likeRepository.delete(likeRepository.findByQuestionAndMember(question, member));
            if (type == 1) question.dislike();
            else question.like();
        }
    }
}
