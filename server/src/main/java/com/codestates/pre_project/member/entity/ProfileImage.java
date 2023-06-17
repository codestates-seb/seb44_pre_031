package com.codestates.pre_project.member.entity;

import com.codestates.pre_project.global.exception.CustomException;
import com.codestates.pre_project.global.exception.ErrorCode;
import com.codestates.pre_project.module.base.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

import java.util.Arrays;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ProfileImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    @Column(name = "unique_name", nullable = false)
    private String uniqueName;
    @Column(name = "original_name", nullable = false)
    private String originalName;
    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    private final static String[] supportedImageType = {"jpg", "jpeg", "gif", "bmp", "png"};

    public ProfileImage(String originalName) {
        this.uniqueName =
        this.originalName = originalName;
    }

    private String toUniqueName(String imageType) {
        return UUID.randomUUID() + "." + imageType;
    }

    private String checkType(String originalName) {
        String type = originalName.substring(originalName.lastIndexOf(".") + 1);
        if (isSupportedType(type)) return type;
        else throw new CustomException(ErrorCode.NOT_SUPPORTED_IMAGE_TYPE);
    }

    // 이미지 타입이 지원하는 타입인지
    private boolean isSupportedType(String imageType) {
        return Arrays
                .stream(supportedImageType)
                .anyMatch(type -> type.equalsIgnoreCase(imageType));
    }
}
