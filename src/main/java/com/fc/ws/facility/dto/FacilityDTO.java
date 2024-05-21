package com.fc.ws.facility.dto;

import com.fc.ws.facility.Facility;

public class FacilityDTO {
    long id;

    String email;

    String image;

    String facilityName;

    String facilityNumber;

    public FacilityDTO(Facility facility) {
        setId(facility.getId());
        setEmail(facility.getEmail());
        setImage(facility.getImage());
        setFacilityName(facility.getFacilityName());
        setFacilityNumber(facility.getFacilityNumber());

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityNumber() {
        return facilityNumber;
    }

    public void setFacilityNumber(String facilityNumber) {
        this.facilityNumber = facilityNumber;
    }
}