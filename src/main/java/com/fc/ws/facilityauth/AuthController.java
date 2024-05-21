package com.fc.ws.facilityauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fc.ws.facilityauth.dto.AuthResponse;
import com.fc.ws.facilityauth.dto.Credentials;

import jakarta.validation.Valid;

@RestController("facilityAuthController")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/api/v1/fauth")
    AuthResponse handleAuthentication(@Valid @RequestBody Credentials creds) {
        return authService.authenticate(creds);
    }

}