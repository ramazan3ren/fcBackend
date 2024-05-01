package com.fc.ws.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fc.ws.auth.dto.AuthResponse;
import com.fc.ws.auth.dto.Credentials;
import com.fc.ws.auth.exception.AuthenticationException;
import com.fc.ws.auth.token.Token;
import com.fc.ws.auth.token.TokenService;
import com.fc.ws.user.User;
import com.fc.ws.user.UserService;
import com.fc.ws.user.dto.UserDTO;

@Service
public class AuthService {

    @Autowired
    UserService userService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    TokenService tokenService;

    public AuthResponse authenticate(Credentials creds) {
        User inDB = userService.findByEmail(creds.email());
        if (inDB == null) {
            throw new AuthenticationException();
        }

        if (!passwordEncoder.matches(creds.password(), inDB.getPassword())) {
            throw new AuthenticationException();
        }

        Token token = tokenService.creaToken(inDB, creds);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUser(new UserDTO(inDB));
        return authResponse;
    }

}
