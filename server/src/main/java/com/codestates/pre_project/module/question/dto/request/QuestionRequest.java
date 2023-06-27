package com.codestates.pre_project.module.question.dto.request;

import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.question.entity.Question;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@ApiModel("Question Request")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionRequest {
    @ApiModelProperty(notes = "제목", example = "질문 제목 입니다.", required = true)
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @ApiModelProperty(notes = "내용", example = "질문 내용 입니다.", required = true)
    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;
    @ApiModelProperty(notes = "태그", example = "[java, javascript]")
    @Nullable
    public String[] tags;

    public Question toEntity() {
        return Question.builder()
                .title(title)
                .content(content)
                .build();
    }
}
