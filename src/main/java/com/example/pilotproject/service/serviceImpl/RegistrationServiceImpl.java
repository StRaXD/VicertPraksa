package com.example.pilotproject.service.serviceImpl;

import com.example.pilotproject.entities.ConfirmationToken;
import com.example.pilotproject.entities.RegistrationRequest;
import com.example.pilotproject.entities.User;
import com.example.pilotproject.repository.ConfirmationTokenRepository;
import com.example.pilotproject.security.CustomUserDetailsService;
import com.example.pilotproject.service.EmailService;
import com.example.pilotproject.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final CustomUserDetailsService customUserDetailsService;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;

    @Override
    public String register(RegistrationRequest request) {
        String token = customUserDetailsService.registerUser(new User(request.getUsername(), request.getPassword(),
                request.getEmail(), false, "ROLE_USER"));
        String link = "http://localhost:8080/api/register/confirm?token=" + token;
        emailService.send(request.getEmail(), emailService.buildEmail(request.getUsername(), link));
        return token;
    }

    @Override
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);
        if (confirmationToken.getConfirmedAt() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already confirmed");
        }
        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Token is expired");
        }
        confirmationTokenRepository.confirmedAt(token, LocalDateTime.now());
        customUserDetailsService.enableUser(confirmationToken.getUser().getEmail());
        return "Token confirmed";
    }
}


