package com.fc.ws.auth.token;

import org.springframework.stereotype.Service;

import com.fc.ws.auth.dto.Credentials;
import com.fc.ws.user.User;

@Service
public interface TokenService {

    public Token creaToken(User user, Credentials creds);
    public User veriyToken(String authorizationHeader);
}
