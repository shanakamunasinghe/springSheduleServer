package com.example.springserver.controller;

import com.example.springserver.dto.UserDTO;
import com.example.springserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/usersData",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> gatAllUsers(){
        return new ResponseEntity<List<UserDTO>>(userService.getAllUsers(), HttpStatus.OK);
    }

    @RequestMapping(value = "/userData/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable int id){
        return new ResponseEntity<UserDTO>(userService.getUser(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/addUser",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<UserDTO>(userService.addUser(userDTO), HttpStatus.OK);
    }
    @RequestMapping(value = "/addAssets",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> addAssets(@RequestBody UserDTO userDTO){
        return new ResponseEntity<Double>(userService.addAssets(userDTO), HttpStatus.OK);
    }
    @RequestMapping(value = "/getAssets",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Double> getAssets(@RequestBody UserDTO userDTO){
        return new ResponseEntity<Double>(userService.getAssets(userDTO), HttpStatus.OK);
    }

    @RequestMapping(value = "/validate",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> validateUser(@RequestBody UserDTO userDTO) {
       return new ResponseEntity<UserDTO> (userService.validateUser(userDTO), HttpStatus.OK);
    }
    @RequestMapping(value = "/deposit",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> depositMoney(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<UserDTO> (userService.depositMoney(userDTO), HttpStatus.OK);
    }
}
