package com.fc.ws.auth.token;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.fc.ws.auth.dto.Credentials;
import com.fc.ws.user.User;

@Service
public class BasicAuthTokenService implements TokenService {

    @Override
    public Token creaToken(User user, Credentials creds) {

        String emailColonPassword = creds.email() + ":" + creds.password();

        String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());

        return new Token("Basic", token);
    }

    @Override
    public User veriyToken(String authorizationHeader) {

        throw new UnsupportedOperationException("Unimplemented method 'veriyToken'");
    }
}
