package com.codestates.pre_project.module.member.service;

import com.codestates.pre_project.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.codestates.pre_project.global.exception.ErrorCode.INTERNAL_SERVER_ERROR;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(String email, String title, String content) {
        SimpleMailMessage emailForm = createEmailForm(email, title, content);

        try {
            javaMailSender.send(emailForm);
        } catch (RuntimeException e) {
            throw new CustomException(INTERNAL_SERVER_ERROR);
        }
    }

    private SimpleMailMessage createEmailForm(String email, String title, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);

        return simpleMailMessage;
    }
}