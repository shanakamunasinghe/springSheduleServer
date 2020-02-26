package com.example.springserver.controller;

import com.example.springserver.dto.UserResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/auth")
public class ApplicationController {

    @RequestMapping("/process")
    public UserResponse process(){
        UserResponse userResponse = new UserResponse();
        userResponse.setContent("processing");
        return userResponse;
    }
}
