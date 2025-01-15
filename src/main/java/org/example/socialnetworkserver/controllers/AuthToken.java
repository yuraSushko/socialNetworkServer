package org.example.socialnetworkserver.controllers;

import org.example.socialnetworkserver.responses.BasicResponse;
import org.example.socialnetworkserver.responses.TokenResponse;
import org.example.socialnetworkserver.services.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class AuthToken {

    private final AuthTokenService authTokenService;
    @Autowired
    public AuthToken(AuthTokenService authTokenService) {
        this.authTokenService = authTokenService;
    }

    @PostMapping("/validateToken")
    public ResponseEntity<TokenResponse> validateToken(@RequestHeader("Authorization") String token) {
        if (token == null || token.isEmpty()) {
            System.out.println("Token missing");
            return ResponseEntity.badRequest().body(new TokenResponse(false, "Token is missing", false,null));
        }

        String cleanToken = token.replace("Bearer ", "");

        TokenResponse response = authTokenService.validateToken(cleanToken);
        return ResponseEntity.ok(response);
    }






}
