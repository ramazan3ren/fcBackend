package com.fc.ws.facilityauth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Credentials(@Email @NotBlank String email, @NotBlank String password) {

}
