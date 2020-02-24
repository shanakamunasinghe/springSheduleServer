package com.example.springserver.controller;

import com.example.springserver.dto.UserResponse;
import com.example.springserver.model.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class UserResponseController {
    @MessageMapping("/content")
    @SendTo("/topic/content")
    public UserResponse getUser(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setContent("Hi " + user.getName());
        return userResponse;
    }
}
