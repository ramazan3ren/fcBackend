package com.fc.ws.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Credentials(@Email @NotBlank String email, @NotBlank String password) {

}
