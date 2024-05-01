package com.fc.ws.auth.dto;

import com.fc.ws.auth.token.Token;
import com.fc.ws.user.dto.UserDTO;

public class AuthResponse {
    UserDTO user;
    
    
    Token token;



    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

}
