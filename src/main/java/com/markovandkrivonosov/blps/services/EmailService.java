package com.markovandkrivonosov.blps.services;

public interface EmailService {

    void sendSimpleMessage(String to, String subject, String text);

}