package com.codestates.pre_project.module.member.dto;

import com.codestates.pre_project.module.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDto {
    @Length(min = 3, max = 15, message = "이름은 3에서 15자 이내")
    private String displayName;
    private String location;
    private String title;
    private String aboutMe;
    private String webLink;
    private String twitterLink;
    private String githubLink;
    private String fullName;

    public static MemberDto of(Member member) {
        return new MemberDto(
                member.getDisplayName(),
                member.getLocation(),
                member.getTitle(),
                member.getAboutMe(),
                member.getWebLink(),
                member.getTwitterLink(),
                member.getGithubLink(),
                member.getFullName()
        );
    }
}
