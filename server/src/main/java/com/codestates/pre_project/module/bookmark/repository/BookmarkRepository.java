package com.codestates.pre_project.module.bookmark.repository;

import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.bookmark.entity.Bookmark;
import com.codestates.pre_project.module.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    boolean existsBookmarkByQuestionIdAndMemberId(Long questionId, Long memberId);
    Bookmark findByQuestionIdAndMemberId(Long questionId, Long memberId);
    List<Bookmark> findAllByMember(Member member);
}
