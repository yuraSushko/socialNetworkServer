package org.example.socialnetworkserver.controllers;

import org.example.socialnetworkserver.responses.BasicResponse;
import org.example.socialnetworkserver.services.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public BasicResponse<String> validateTokenToUser(@RequestHeader("Authorization") String token ){
       return authTokenService.validateTokenToUser(token);
    }






}
