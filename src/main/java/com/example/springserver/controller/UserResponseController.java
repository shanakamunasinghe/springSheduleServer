package com.example.springserver.controller;

import com.example.springserver.dto.UserResponse;
import com.example.springserver.model.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserResponseController {
    @MessageMapping("/hello")
    @SendTo("/topic/content")
    public UserResponse getUser(String user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setContent("Hi " + user);
        return userResponse;
    }
}
