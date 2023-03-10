package com.example.pilotproject.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    void send(String to, String email);

    String buildEmail(String email, String link);
}
