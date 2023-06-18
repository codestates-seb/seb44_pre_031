package com.codestates.pre_project.module.member.dto;

import com.codestates.pre_project.module.member.entity.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDto {
    private Long id;
    private String displayName;

    public static MemberDto of(Member member) {
        return new MemberDto(member.getId(), member.getDisplayName());
    }
}
