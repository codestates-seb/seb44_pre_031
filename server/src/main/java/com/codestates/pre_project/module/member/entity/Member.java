package com.codestates.pre_project.module.member.entity;

import com.codestates.pre_project.module.member.dto.UpdateMemberDto;
import com.codestates.pre_project.module.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE members SET is_deleted = true WHERE member_id = ?")
@Where(clause = "is_deleted = false")
@Entity
@Table(name = "members")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "display_name", nullable = false, unique = true)
    private String displayName;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Column(name = "full_name")
    private String fullName;
    @Column(name = "reputation")
    private int reputation;
    @Column(name = "location")
    private String location;
    @Column(name = "title")
    private String title;
    @Column(name = "about_me")
    private String aboutMe;
    @Column(name = "web_link")
    private String webLink;
    @Column(name = "twitter_link")
    private String twitterLink;
    @Column(name = "github_link")
    private String githubLink;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    public static Member updateMemberInfo(Member findMember, UpdateMemberDto dto) {
        findMember.displayName = dto.getDisplayName();
        findMember.fullName = dto.getFullName();
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
    }
}
