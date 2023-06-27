package com.codestates.pre_project.module.member.dto;

import com.codestates.pre_project.module.member.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@ApiModel("SignUp")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    @ApiModelProperty(notes = "회원 이메일", example = "test@gmail.com", required = true)
    @NotBlank
    @Email(message = "올바른 이메일 형태로 입력해주세요")
    private String email;
    @ApiModelProperty(notes = "이메일 인증코드", example = "123456", required = true)
    @NotBlank
    private String code;
    @ApiModelProperty(notes = "display name", example = "limjeongmin", required = true)
    @NotBlank
    @Length(min = 3, max = 15, message = "이름은 3에서 15자 이내")
    private String displayName;
    @ApiModelProperty(notes = "비밀번호", example = "1q2w3e@", required = true)
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9!@#$%^&*])[a-z0-9!@#$%^&*]{9,20}$",
            message = "영문 소문자 9 ~ 12자 특수문자 숫자 포함")
    private String password;

    public static Member toEntity(SignUpDto request) {
        return Member.builder()
                .email(request.getEmail())
                .code(request.getCode())
                .displayName(request.getDisplayName())
                .password(request.getPassword())
                .build();
    }
}
