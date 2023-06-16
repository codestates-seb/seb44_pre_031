package com.codestates.pre_project.member.entity;

import com.codestates.pre_project.module.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO : 이미지는 나중에
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "members")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "member_id")
    private Long id;

    // 회원가입
    @Column(name = "display_name", nullable = false, unique = true)
    private String displayName; // 닉네임
    @Column(name = "email", nullable = false, unique = true)
    private String email; // 이메일
    @Column(name = "password", nullable = false)
    private String password; // 비밀번호
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    // 프로필
    @Column(name = "full_name")
    private String fullName; // 실명
    @Column(name = "reputation")
    private int reputation; // 평판점수
    @Column(name = "location")
    private String location; // 주소
    @Column(name = "title")
    private String title; // 프로필 제목
    @Column(name = "about_me")
    private String aboutMe; // 내 소개
    @Column(name = "web_link")
    private String webLink; // 웹링크
    @Column(name = "twitter_link")
    private String twitterLink; // 트위터 링크
    @Column(name = "github_link")
    private String githubLink; // 깃헙 링크

    public static Member updateMemberInfo(Member findMember, Member dto) {
        findMember.displayName = dto.getDisplayName();
        findMember.fullName = dto.getFullName();
        findMember.reputation = dto.getReputation();
        findMember.location = dto.getLocation();
        findMember.title = dto.getTitle();
        findMember.aboutMe = dto.getAboutMe();
        findMember.webLink = dto.getWebLink();
        findMember.twitterLink = dto.getTwitterLink();
        findMember.githubLink = dto.getGithubLink();

        return findMember;
    }

    public Member(Long id, String email, String password, List<String> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }  // Builder를 사용하지 않기 위함
}
