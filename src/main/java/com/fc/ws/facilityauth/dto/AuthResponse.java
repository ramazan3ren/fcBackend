package com.fc.ws.facilityauth.dto;

import com.fc.ws.facility.dto.FacilityDTO;
import com.fc.ws.facilityauth.token.Token;

public class AuthResponse {

    FacilityDTO facility;

    Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public FacilityDTO getFacility() {
        return facility;
    }

    public void setFacility(FacilityDTO facility) {
        this.facility = facility;
    }

}
