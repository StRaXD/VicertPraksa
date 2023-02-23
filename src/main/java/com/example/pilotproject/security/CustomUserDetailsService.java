package com.example.pilotproject.security;

import com.example.pilotproject.entities.ConfirmationToken;
import com.example.pilotproject.entities.User;
import com.example.pilotproject.repository.ConfirmationTokenRepository;
import com.example.pilotproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User loadedUser;
        boolean exists = userRepository.existsByUsername(username);
        if (!exists) {
            throw new UsernameNotFoundException("Not found" + username);
        } else
            loadedUser = userRepository.findByUsername(username);
        return new CustomUserDetails(loadedUser);
    }

    public String registerUser(User user) {
        boolean exists = userRepository.existsByEmail(user.getEmail());
        if (exists) {
            throw new IllegalStateException("Email is already taken");
        } else {
            String cryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(cryptedPassword);
            userRepository.save(user);
            String token = UUID.randomUUID().toString();
            ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(15), user);
            confirmationTokenRepository.save(confirmationToken);
            return token;
        }
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }
}
