package com.codestates.pre_project.module.question.repository;

import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.entity.QuestionLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<QuestionLike, Long> {
    @Query("SELECT v FROM QuestionLike v " +
            "JOIN v.question q " +
            "JOIN v.member m " +
            "WHERE q.id = :questionId " +
            "AND m.id = :memberId " +
            "AND v.voteType = :type")
    Optional<QuestionLike> findVoteTypeByQuestionAndMember(@Param("questionId") Long questionId,
                                                           @Param("memberId") Long memberId,
                                                           @Param("type") int type);
    boolean existsQuestionLikeByQuestionIdAndMemberIdAndVoteType(Long questionId, Long memberId, int voteType);
    QuestionLike findByQuestionAndMember(Question question, Member member);
}
