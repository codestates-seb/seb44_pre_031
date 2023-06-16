package com.codestates.pre_project.module.bookmark.repository;

import com.codestates.pre_project.module.bookmark.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
