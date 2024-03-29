package com.fc.ws.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    void save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            userRepository.save(user);

        } catch (DataIntegrityViolationException ex) {
            if (ex.getMessage().contains("username")) {
                throw new NotUniqueUsernameException();
            } else if (ex.getMessage().contains("email")) {
                throw new NotUniqueEmailException();
            } else {
                throw new RuntimeException("Kullanıcı oluşturulurken bilinmeyen bir hata oluştu.");
            }

        }
    }
}
