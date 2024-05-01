package com.fc.ws.user;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fc.ws.email.EmailService;
import com.fc.ws.user.exception.ActivationNotificationException;
import com.fc.ws.user.exception.InvalidTokenException;
import com.fc.ws.user.exception.NotUniqueEmailException;
import com.fc.ws.user.exception.NotUniqueUsernameException;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    EmailService emailService;

    @Transactional(rollbackOn = MailException.class)
    void save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActivationToken(UUID.randomUUID().toString());
            userRepository.save(user);
            emailService.sendActivationEmail(user.getEmail(), user.activationToken, user.getUsername());

        } catch (DataIntegrityViolationException ex) {
            if (ex.getMessage().contains("username")) {
                throw new NotUniqueUsernameException();
            } else if (ex.getMessage().contains("email")) {
                throw new NotUniqueEmailException();
            } else {
                throw new RuntimeException("Kullanıcı oluşturulurken bilinmeyen bir hata oluştu.");
            }

        } catch (MailException ex) {

            throw new ActivationNotificationException();

        }
    }

    public void activateUser(String token) {

        User inDB = userRepository.findByActivationToken(token);
        if (inDB == null) {
            throw new InvalidTokenException();
        }
        inDB.setActive(true);
        inDB.setActivationToken(null);
        userRepository.save(inDB);
    }

    public Page<User> getUsers(Pageable page) {
        return userRepository.findAll(page);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
