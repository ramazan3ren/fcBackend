package com.fc.ws.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fc.ws.error.ApiError;
import com.fc.ws.shared.GenericMessage;
import com.fc.ws.user.dto.UserCreate;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> handleMethodArgNotValidEx(MethodArgumentNotValidException exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage("Validation Error");
        apiError.setStatus(400);
        Map<String, String> validationErrors = new HashMap<>();

        for (var fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(NotUniqueEmailException.class)
    ResponseEntity<ApiError> handleNotUniqueEmailEx(NotUniqueEmailException exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage("Validation Error");
        apiError.setStatus(400);
        Map<String, String> validationErrors = new HashMap<>();
        validationErrors.put("email", "Bu e-posta adresi kullanımda.");
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(NotUniqueUsernameException.class)
    ResponseEntity<ApiError> handleNotUniqueUsernameEx(NotUniqueUsernameException exception) {
        ApiError apiError = new ApiError();
        apiError.setPath("/api/v1/users");
        apiError.setMessage("Validation Error");
        apiError.setStatus(400);
        Map<String, String> validationErrors = new HashMap<>();
        validationErrors.put("username", "Bu kullanıcı adı maalesef kayıtlı.");
        apiError.setValidationErrors(validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }
}
