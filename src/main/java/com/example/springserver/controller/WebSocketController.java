package com.example.springserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @Autowired
    SimpMessagingTemplate template;
    @MessageMapping("/message")
    @SendTo("/topic/stockData")
    public void getStockData (String name){
        template.convertAndSend("/topic/stockData", "HI : "+name);
    }
}
