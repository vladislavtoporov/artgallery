package ru.kpfu.itis.artgallery.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.artgallery.services.EmailService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private Session session;

    @Autowired
    public EmailServiceImpl(Session session) {
        this.session = session;


    }

    public void sendSimpleMessage(String to, String subject, String text) {
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setSubject(subject);
            msg.setContent(text, "text/html");
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

