package org.example.socialnetworkserver.controllers;


import org.example.socialnetworkserver.enteties.User;
import org.example.socialnetworkserver.responses.BasicResponse;
import org.example.socialnetworkserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping("/testAlive")
    public String testAlive() {
        return "Alive";
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {

        return userService.getAllUsers();
    }

    @GetMapping("/signIn")
    public BasicResponse signIn(@RequestParam String userName, @RequestParam String password) {
        return userService.signIn(userName,  password);
    }




    @PostMapping("/signUp")
    public BasicResponse createUser(@RequestBody User user) {
        return userService.createUser(user);

    }

    @GetMapping("/cookie")
    public BasicResponse testCookie(@CookieValue("token") String tokenCookie){
        return new BasicResponse(true, "token: "+ tokenCookie);
    }




}