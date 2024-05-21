package com.fc.ws.facility.dto;

import com.fc.ws.facility.Facility;
import com.fc.ws.user.validation.UniqueEmail;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record FacilityCreate(

        @NotBlank @Email @UniqueEmail @Column(unique = true) String email,

        @Size(min = 8, max = 255) @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$") String password) {

    public Facility toFacility() {
        Facility facility = new Facility();
        facility.setEmail(email);
        facility.setPassword(password);
        return facility;
    }
}
