package com.codestates.pre_project.module.answer.dto.request;

import com.codestates.pre_project.module.answer.entity.Answer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;

@ApiModel("Answer Request")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerRequest {
    @ApiModelProperty(notes = "답변 내용", example = "답변 내용 입니다.", required = true)
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    public Answer toEntity() {
        return Answer.builder()
                .content(content)
                .build();
    }
}
