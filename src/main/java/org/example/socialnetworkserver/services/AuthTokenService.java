package org.example.socialnetworkserver.services;

import lombok.Setter;
import org.example.socialnetworkserver.enteties.User;
import org.example.socialnetworkserver.responses.BasicResponse;
import org.example.socialnetworkserver.responses.TokenResponse;
import org.example.socialnetworkserver.utils.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    public TokenResponse validateToken(String cleanToken) {
        boolean isValid = JwtUtil.validateToken(cleanToken);
        String username = "";
        if (isValid){
            username = JwtUtil.getTokensValue(cleanToken);
        }

        return new TokenResponse(isValid, isValid ? "Token is valid" : "Token is invalid", isValid,username);
    }
}
