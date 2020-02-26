package com.example.springserver.controller;

import com.example.springserver.dto.UserDTO;
import com.example.springserver.dto.UserResponse;
import com.example.springserver.model.User;
import com.example.springserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("secure/rest")
public class AdminController {

    @Autowired
    public UserService userService;

    @PreAuthorize("hasAnyRole('admin')")
    @RequestMapping("/admin/add")
    public UserResponse addUserByAdmin(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
        UserResponse userResponse = new UserResponse();
        userResponse.setContent("done creating admin user");
        return userResponse;
    }
}
