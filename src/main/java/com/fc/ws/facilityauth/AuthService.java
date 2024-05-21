package com.fc.ws.facilityauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fc.ws.facility.Facility;
import com.fc.ws.facility.FacilityService;
import com.fc.ws.facility.dto.FacilityDTO;
import com.fc.ws.facilityauth.dto.AuthResponse;
import com.fc.ws.facilityauth.dto.Credentials;
import com.fc.ws.facilityauth.exception.AuthenticationException;
import com.fc.ws.facilityauth.token.Token;
import com.fc.ws.facilityauth.token.TokenService;


@Service("facilityAuthService")
public class AuthService {

    @Autowired
    FacilityService facilityService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    TokenService tokenService;

    public AuthResponse authenticate(Credentials creds) {
        Facility inDB = facilityService.findByEmail(creds.email());
        if (inDB == null) {
            throw new AuthenticationException();
        }

        if (!passwordEncoder.matches(creds.password(), inDB.getPassword())) {
            throw new AuthenticationException();
        }

        Token token = tokenService.creaToken(inDB, creds);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setFacility(new FacilityDTO(inDB));
        return authResponse;
    }

}
