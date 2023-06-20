package com.codestates.pre_project.module.member.service;

import com.codestates.pre_project.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.pre_project.global.exception.ErrorCode.INTERNAL_SERVER_ERROR;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(String to, String title, String content) {
        SimpleMailMessage emailForm = createEmailForm(to, title, content);

        try {
            javaMailSender.send(emailForm);
        } catch (RuntimeException e) {
            log.debug("MailService.sendEmail exception occur to: {}, title: {}, content: {}", to, title, content);
            throw new CustomException(INTERNAL_SERVER_ERROR);
        }
    }

    private SimpleMailMessage createEmailForm(String to, String title, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);

        return simpleMailMessage;
    }

}