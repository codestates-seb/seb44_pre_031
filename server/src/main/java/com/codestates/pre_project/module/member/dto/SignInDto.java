package com.codestates.pre_project.module.member.dto;

import com.codestates.pre_project.module.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SignInDto {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignInRequest {
        @NotBlank
        @Email
        private String email;
        @NotBlank
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9!@#$%^&*])[a-z0-9!@#$%^&*]{9,12}$"
                , message = "영문 소문자 9 ~ 12자 특수문자 숫자 포함")
        private String password;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignInResponse {
        /*
            TODO : 요구사항에 따라 수정
         */
        private Long id;
        private String displayName;
        private String email;
    }

    public static Member toEntity(SignInRequest request) {
        return Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public static SignInResponse toResponse(Member member) {
        return SignInResponse.builder()
                .id(member.getId())
                .displayName(member.getDisplayName())
                .email(member.getEmail())
                .build();
    }
}
