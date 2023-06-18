package com.codestates.pre_project.module.question.dto.response;

import com.codestates.pre_project.module.question.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSimpleDto {

    private Long id;
    private String title;
    private String displayName;
    private Long voteCount;
    private Long bookmarkCount;

    public static QuestionSimpleDto toDto(Question question) {
        return new QuestionSimpleDto(question.getId(), question.getTitle(), question.getMember().getDisplayName(), question.getVoteCount(),
                question.getBookmarkCount());
    }
}
