package com.example.springserver.controller;


import com.example.springserver.dto.StockDTO;
import com.example.springserver.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StockController {

    @Autowired
    public StockService stockService;

    @RequestMapping(value = "/stocksData",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StockDTO>> getAllStocks(){
        return new ResponseEntity<List<StockDTO>>(stockService.getAllStocks(), HttpStatus.OK);
    }

    @RequestMapping(value = "/stockData/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> getStock(@PathVariable int id){
        return new ResponseEntity<StockDTO>(stockService.getStock(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/addStock",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> addStock(@RequestBody StockDTO stockDTO){
        return new ResponseEntity<StockDTO>(stockService.addStock(stockDTO), HttpStatus.OK);
    }
    @RequestMapping(value = "/updateStock",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> updateStock(@RequestBody StockDTO stockDTO){
        return new ResponseEntity<StockDTO>(stockService.editStock(stockDTO), HttpStatus.OK);
    }
}
