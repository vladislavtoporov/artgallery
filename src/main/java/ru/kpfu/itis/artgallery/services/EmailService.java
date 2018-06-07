package ru.kpfu.itis.artgallery.services;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);

}
