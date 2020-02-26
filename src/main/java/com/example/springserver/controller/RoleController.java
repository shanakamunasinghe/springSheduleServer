package com.example.springserver.controller;

import com.example.springserver.dto.RoleDTO;
import com.example.springserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    public RoleService roleService;

    @RequestMapping(value = "/rolesData",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDTO>> getAllRoles(){
        return new ResponseEntity<List<RoleDTO>>(roleService.getAllRoles(), HttpStatus.OK);
    }

    @RequestMapping(value = "/roleData/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDTO> getRole(@PathVariable int id){
        return new ResponseEntity<RoleDTO>(roleService.getRole(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/addRole",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDTO> addRole(@RequestBody RoleDTO roleDTO){
        return new ResponseEntity<RoleDTO>(roleService.addRole(roleDTO), HttpStatus.OK);
    }
}
