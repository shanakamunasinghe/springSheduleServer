package com.example.springserver.controller;


import com.example.springserver.dto.StockDTO;
import com.example.springserver.dto.StockTransactionDTO;
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

    @RequestMapping(value = "/addStockToUser",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> addStockToUser(@RequestBody StockTransactionDTO stockTransactionDTO){
        return new ResponseEntity<Integer>(userStockLogService.addStockToUser(stockTransactionDTO.getUser_id(),
                stockTransactionDTO.getStock_id(),stockTransactionDTO.getStock_shares(),stockTransactionDTO.getStock_price()),HttpStatus.OK);
    }

    @RequestMapping(value = "/updateUserStock",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> updateUserStock(@RequestBody StockTransactionDTO stockTransactionDTO){
        return new ResponseEntity<Integer>(userStockLogService.updateUserStock(stockTransactionDTO.getUser_id(),
                stockTransactionDTO.getStock_id(),stockTransactionDTO.getStock_shares(),stockTransactionDTO.getStock_price(),stockTransactionDTO.getBuyOrSell()),HttpStatus.OK);
    }
}
