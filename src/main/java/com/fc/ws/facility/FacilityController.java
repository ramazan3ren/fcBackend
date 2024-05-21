package com.fc.ws.facility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fc.ws.error.ApiError;
import com.fc.ws.facility.dto.FacilityCreate;
import com.fc.ws.facility.dto.FacilityDTO;
import com.fc.ws.shared.GenericMessage;

import jakarta.validation.Valid;

@RestController
public class FacilityController {

    @Autowired
    FacilityService facilityService;

    @PostMapping("api/v1/facility")
    GenericMessage createFacility(@Valid @RequestBody FacilityCreate facility) {
        facilityService.save(facility.toFacility());
        return new GenericMessage("Hesabınız Oluşturuldu : " + facility.toFacility().facilityName);
    }

    @PatchMapping("api/v1/facility/{token}/active")
    GenericMessage activateFacility(@PathVariable String token) {
        facilityService.activateFacility(token);
        return new GenericMessage("Hesabınız Aktif Edildi!");
    }

    @GetMapping("api/v1/facility")
    Page<FacilityDTO> getUsers(Pageable page) {
        return facilityService.getFacilities(page).map(FacilityDTO::new);
    }

    @GetMapping("/api/v1/facility/{facilityNumber}")
    public ResponseEntity<?> getFacilityByToken(@PathVariable String facilityNumber) {
        Facility facility = facilityService.getFacility(facilityNumber);
        if (facility == null) {
            ApiError apiError = new ApiError();
            apiError.setPath("/api/v1/facility/" + facilityNumber);
            apiError.setMessage(facilityNumber + " numaralı tesis bulunamadı.");
            apiError.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        }
        return ResponseEntity.ok(new FacilityDTO(facility));
    }
}
