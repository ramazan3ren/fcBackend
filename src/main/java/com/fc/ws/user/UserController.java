package com.fc.ws.user;

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
import com.fc.ws.shared.GenericMessage;
import com.fc.ws.user.dto.UserCreate;
import com.fc.ws.user.dto.UserDTO;

import jakarta.validation.Valid;

@RestController

public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("api/v1/users")
    GenericMessage createUser(@Valid @RequestBody UserCreate user) {
        userService.save(user.toUser());
        return new GenericMessage("Hesabınız Oluşturuldu : " + user.toUser().username);
    }

    @PatchMapping("api/v1/users/{token}/active")
    GenericMessage activateUser(@PathVariable String token) {
        userService.activateUser(token);
        return new GenericMessage("Hesabınız Aktif Edildi!");
    }

    @GetMapping("api/v1/users")
    Page<UserDTO> getUsers(Pageable page) {
        return userService.getUsers(page).map(UserDTO::new);
    }


    @GetMapping("api/v1/users/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        User user = userService.getUser(username);
        if (user == null) {
            ApiError apiError = new ApiError();
            apiError.setPath("/api/v1/users/" + username);
            apiError.setMessage(username + " adlı kullanıcı bulunamadı.");
            apiError.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        }
        return ResponseEntity.ok(new UserDTO(user));
    }

    @PostMapping("api/v1/users/info")
    public ResponseEntity<?> getUserInfoByUsername(@RequestBody User username) {
        User user = userService.getUser(username.getUsername());
        if (user == null) {
            ApiError apiError = new ApiError();
            apiError.setPath("/api/v1/users/info");
            apiError.setMessage(username + " adlı kullanıcı bulunamadı.");
            apiError.setStatus(HttpStatus.NOT_FOUND.value());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
        }
        return ResponseEntity.ok(user);
    }

}
