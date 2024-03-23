package com.fc.ws.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    @PostMapping("api/v1/users")
    void userCreate(@RequestBody User user) {
        System.err.println(user);
    }
}
