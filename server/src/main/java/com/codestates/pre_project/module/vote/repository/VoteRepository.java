package com.codestates.pre_project.module.vote.repository;

import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    @Query("SELECT v FROM Vote v " +
            "JOIN v.question q " +
            "JOIN v.member m " +
            "WHERE q.id = :questionId " +
            "AND m.id = :memberId " +
            "AND v.voteType = :type")
    Optional<Vote> findVoteTypeByQuestionAndMember(@Param("questionId") Long questionId,
                                                   @Param("memberId") Long memberId,
                                                   @Param("type") boolean type);

    Vote findByQuestionAndMember(Question question, Member member);
}
