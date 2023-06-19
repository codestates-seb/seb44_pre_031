package com.codestates.pre_project.module.question.dto.response;

import com.codestates.pre_project.module.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkedQuestionsResponse {

    private Long id;
    private String title;
    private String displayName;
    private Long voteCount;
    private Long bookmarkCount;

    public static BookmarkedQuestionsResponse toDto(Question question) {
        return new BookmarkedQuestionsResponse(question.getId(), question.getTitle(), question.getMember().getDisplayName(), question.getLikeCount(),
                question.getBookmarkCount());
    }
}
