package com.example.springserver.controller;


import com.example.springserver.dto.StockDTO;
import com.example.springserver.dto.UserStockLogDTO;
import com.example.springserver.service.UserStockLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserStockLogController {
    @Autowired
    public UserStockLogService userStockLogService;

    @RequestMapping(value = "/stockDataLogs/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserStockLogDTO>> getStockLogs(@PathVariable int id){
        return new ResponseEntity<List<UserStockLogDTO>>(userStockLogService.getAllUserLogs(id), HttpStatus.OK);

    }
}
