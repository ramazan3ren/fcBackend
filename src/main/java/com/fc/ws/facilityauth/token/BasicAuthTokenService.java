package com.fc.ws.facilityauth.token;

import java.util.Base64;

import org.springframework.stereotype.Service;

import com.fc.ws.facility.Facility;
import com.fc.ws.facilityauth.dto.Credentials;

@Service("facilityTokenService")
public class BasicAuthTokenService implements TokenService {

    @Override
    public Token creaToken(Facility facility, Credentials creds) {

        String emailColonPassword = creds.email() + ":" + creds.password();

        String token = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());

        return new Token("Basic", token);
    }

    @Override
    public Facility veriyToken(String authorizationHeader) {

        throw new UnsupportedOperationException("Unimplemented method 'veriyToken'");
    }
}