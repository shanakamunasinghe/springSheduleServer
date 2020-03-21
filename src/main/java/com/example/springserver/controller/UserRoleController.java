package com.example.springserver.controller;

import com.example.springserver.dto.RoleDTO;
import com.example.springserver.service.UserRolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRoleController {

    @Autowired
    private UserRolesService userRolesService;

    @RequestMapping(value = "/userRolesData/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDTO>> getAllUserRoles(@PathVariable int id){
        return new ResponseEntity<>(userRolesService.findByUserId(id), HttpStatus.OK);
    }

}
