package com.codestates.pre_project.module.vote.repository;

import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.vote.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Vote findByQuestionAndMember(Question question, Member member);
}
