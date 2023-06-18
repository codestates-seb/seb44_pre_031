package com.codestates.pre_project.module.bookmark.repository;

import com.codestates.pre_project.member.entity.Member;
import com.codestates.pre_project.module.bookmark.entity.Bookmark;
import com.codestates.pre_project.module.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Bookmark findByQuestionAndMember(Question question, Member member);
    Bookmark findBookmarkByQuestion(Question question);
    List<Bookmark> findAllByMember(Member member);
}
