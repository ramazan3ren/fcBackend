package com.fc.ws.facility;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fc.ws.email.EmailServiceFacility;
import com.fc.ws.facility.exception.NotUniqueEmail;
import com.fc.ws.facility.exception.ActivationNotificationException;
import com.fc.ws.facility.exception.InvalidTokenException;

import jakarta.transaction.Transactional;

@Service
public class FacilityService {

    @Autowired
    FacilityRepository facilityRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    EmailServiceFacility emailServiceFacility;

    @Transactional(rollbackOn = MailException.class)
    void save(Facility facility) {
        try {
            facility.setPassword(passwordEncoder.encode(facility.getPassword()));
            facility.setActivationToken(UUID.randomUUID().toString());
            facilityRepository.save(facility);
            emailServiceFacility.sendActivationEmail(facility.getEmail(), facility.activationToken,
                    facility.getFacilityName());

        } catch (DataIntegrityViolationException ex) {
            if (ex.getMessage().contains("email")) {
                throw new NotUniqueEmail();
            } else {
                throw new RuntimeException("Kullanıcı oluşturulurken bilinmeyen bir hata oluştu.");
            }

        } catch (MailException ex) {

            throw new ActivationNotificationException();

        }
    }

    public void activateFacility(String token) {

        Facility inDB = facilityRepository.findByActivationToken(token);
        if (inDB == null) {
            throw new InvalidTokenException();
        }
        inDB.setActive(true);
        inDB.setActivationToken(null);
        facilityRepository.save(inDB);
    }

    public Page<Facility> getFacilities(Pageable page) {
        return facilityRepository.findAll(page);
    }

    public Facility getFacility(String facilityNumber) {
        return facilityRepository.findByFacilityNumber(facilityNumber);
    }

    public Facility findByEmail(String email) {
        return facilityRepository.findByEmail(email);
    }

}
