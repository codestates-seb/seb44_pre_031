package com.codestates.pre_project.module.member.dto;

import com.codestates.pre_project.module.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Length(min = 3, max = 15, message = "이름은 3에서 15자 이내")
    private String displayName;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9!@#$%^&*])[a-z0-9!@#$%^&*]{9,12}$"
            , message = "영문 소문자 9 ~ 12자 특수문자 숫자 포함")
    private String password;


    public static Member toEntity(SignUpDto request) {
        return Member.builder()
                .email(request.getEmail())
                .displayName(request.getDisplayName())
                .password(request.getPassword())
                .build();
    }
}
