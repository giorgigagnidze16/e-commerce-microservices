package com.ecom.microservice.service;

import java.io.IOException;
import java.nio.charset.Charset;

import com.ecom.microservice.api.model.NotificationEvent;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailingService {
    private static final String WELCOME_SUBJECT = "Welcome!";
    private final JavaMailSender mailSender;

    @EventListener
    public void onNotificationEvent(NotificationEvent event) throws MessagingException, IOException {
        log.info("Received notification event type {}", event.type());
        switch (event.type()) {
            case WELCOME_MESSAGE -> send(event.details().to(), WELCOME_SUBJECT, new ClassPathResource("welcome.html"));
            case SHIPMENT, ORDER, REVIEW -> throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    public void send(String to, String subject, Resource resource) throws MessagingException, IOException {
        log.info("Sending an email to {}", to);
        var message = mailSender.createMimeMessage();
        message.setSubject(subject);
        message.setRecipients(Message.RecipientType.TO, to);
        message.setContent(resource.getContentAsString(Charset.defaultCharset()), "text/html");
        mailSender.send(message);
    }
}
