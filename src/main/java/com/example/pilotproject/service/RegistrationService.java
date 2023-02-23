package com.example.pilotproject.service;

import com.example.pilotproject.entities.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    String register(RegistrationRequest request);

    String confirmToken(String token);
}
