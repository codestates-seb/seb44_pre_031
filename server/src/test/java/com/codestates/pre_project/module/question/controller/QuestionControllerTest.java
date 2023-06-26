package com.codestates.pre_project.module.question.controller;

import com.codestates.pre_project.base.IntegrationTest;
import com.codestates.pre_project.global.config.SecurityConfiguration;
import com.codestates.pre_project.module.member.controller.MemberController;
import com.codestates.pre_project.module.member.entity.Member;
import com.codestates.pre_project.module.member.repository.MemberRepository;
import com.codestates.pre_project.module.question.entity.Question;
import com.codestates.pre_project.module.question.entity.QuestionTag;
import com.codestates.pre_project.module.question.repository.QuestionRepository;
import com.codestates.pre_project.module.question.repository.QuestionTagRepository;
import com.codestates.pre_project.module.tag.repository.TagRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@WebMvcTest(
        controllers = {MemberController.class},
        excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        classes = SecurityConfiguration.class)
        }
)
class QuestionControllerTest extends IntegrationTest {
    private static final String BASE_URI = "/api/questions";

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private QuestionTagRepository questionTagRepository;

    private Member member;
    private Question question;
    private QuestionTag questionTag;

    @BeforeEach
    void setUp() {
        final String email = "test@gmail.com";
        final String displayName = "test";
        final String password = "testpassword123!";

        member = memberRepository.save(Member.builder()
                .email(email)
                .displayName(displayName)
                .password(password)
                .build());
    }

    @Nested
    class 게시글_작성_테스트 {
        private final String title = "test title";
        private final String content = "test content";
    }
}
