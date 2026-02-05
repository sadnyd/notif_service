package com.example.notifservice.gateway;

import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class SmtpGateway implements MessageGateway {

    private final JavaMailSender mailSender;

    public SmtpGateway(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(String message, String recipient) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(recipient);
        mail.setSubject("Notification from Bank");
        mail.setText(message);
        mailSender.send(mail);
    }
}
