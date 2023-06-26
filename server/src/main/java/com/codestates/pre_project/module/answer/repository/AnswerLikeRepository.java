package com.codestates.pre_project.module.answer.repository;

import com.codestates.pre_project.module.answer.entity.AnswerLike;
import com.codestates.pre_project.module.answer.entity.enums.VoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerLikeRepository extends JpaRepository<AnswerLike, Long> {
    Optional<AnswerLike> findByAnswerIdAndMemberId(Long answerId, Long memberId);
    Long countByAnswerIdAndVoteStatus(Long answerId, VoteStatus voteStatus);
}
