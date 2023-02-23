package com.example.pilotproject.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class RegistrationRequest {
    private String username;
    private String password;
    private String email;
}
