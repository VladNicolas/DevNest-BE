package com.nicoals.DevNestBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmailWithToken(String userEmail, String link) throws MessagingException {
        
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String subject = "Joint the project!";
        String test = "Click the link to joint the project team." + link;

        helper.setSubject(subject);
        helper.setText(test);
        helper.setTo(userEmail);

        try {
            javaMailSender.send(mimeMessage);
        }
        catch (MailSendException e) {
            throw new MailSendException("Failed to send email.");
        }
    }

}
