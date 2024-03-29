package com.fc.ws.user.dto;

import com.fc.ws.user.User;
import com.fc.ws.user.validation.UniqueEmail;
import com.fc.ws.user.validation.UniqueUsername;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreate(
        @NotBlank @Size(min = 4, max = 255) @Column(unique = true) @UniqueUsername String username,

        @NotBlank @Email @UniqueEmail @Column(unique = true) String email,

        @Size(min = 8, max = 255) @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$") String password) {

    public User toUser() {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

}
