package com.fc.ws.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fc.ws.auth.dto.AuthResponse;
import com.fc.ws.auth.dto.Credentials;

import jakarta.validation.Valid;

@RestController("userAuthController")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/api/v1/auth")
    AuthResponse handleAuthentication(@Valid @RequestBody Credentials creds) {
        return authService.authenticate(creds);
    }

}
