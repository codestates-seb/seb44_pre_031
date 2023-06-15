package com.codestates.pre_project.member.dto;

import com.codestates.pre_project.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMemberDto {

    @Length(min = 3, max = 15, message = "이름은 3에서 15자 이내")
    private String displayName;
    private String location;
    private String title;
    private String aboutMe;
    private String webLink;
    private String twitterLink;
    private String githubLink;
    private String fullName;

    public static UpdateMemberDto toDto(Member member) {
        return UpdateMemberDto.builder()
                .displayName(member.getDisplayName())
                .location(member.getLocation())
                .title(member.getTitle())
                .aboutMe(member.getAboutMe())
                .webLink(member.getWebLink())
                .twitterLink(member.getTwitterLink())
                .githubLink(member.getGithubLink())
                .fullName(member.getFullName())
                .build();
    }

    public static Member toEntity(UpdateMemberDto dto) {
        return Member.builder()
                .displayName(dto.getDisplayName())
                .location(dto.getLocation())
                .title(dto.getTitle())
                .aboutMe(dto.getAboutMe())
                .webLink(dto.getWebLink())
                .twitterLink(dto.getTwitterLink())
                .githubLink(dto.getGithubLink())
                .fullName(dto.getFullName())
                .build();
    }
}
