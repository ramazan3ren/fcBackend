package com.fc.ws.facilityauth.token;

import org.springframework.stereotype.Service;

import com.fc.ws.facility.Facility;
import com.fc.ws.facilityauth.dto.Credentials;

@Service("facilityTokenService")
public interface TokenService {

    public Token creaToken(Facility facility, Credentials creds);

    public Facility veriyToken(String authorizationHeader);
}
