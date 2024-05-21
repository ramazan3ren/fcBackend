package com.fc.ws.facility;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
    Facility findByEmail(String email);

    Facility findByActivationToken(String token);

    Facility findByFacilityNumber(String facilityNumber);
}
