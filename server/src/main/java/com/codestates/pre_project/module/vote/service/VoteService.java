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

import static com.codestates.pre_project.global.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final QuestionRepository questionRepository;
    private final VoteRepository voteRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void voteQuestion(Long questionId, boolean type) throws CustomException{
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new CustomException(QUESTION_NOT_FOUND));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName()).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        if (voteRepository.findVoteTypeByQuestionAndMember(question.getId(), member.getId(), type).isPresent()) {
            throw new CustomException(REQUEST_VALIDATION_FAIL);
        }

        if (type) {
            deleteIfAlreadyVotes(question, member, false);
            question.vote();
            voteRepository.save(Vote.of(question, member, true));
        } else {
            deleteIfAlreadyVotes(question, member, true);
            question.cancelVote();
            voteRepository.save(Vote.of(question, member, false));
        }
    }

    private void deleteIfAlreadyVotes(Question question, Member member, boolean type) {
        if (voteRepository.findVoteTypeByQuestionAndMember(question.getId(), member.getId(), type).isPresent()) {
            voteRepository.delete(voteRepository.findByQuestionAndMember(question, member));
            if (type) question.cancelVote();
            else question.vote();
        }
    }
}
