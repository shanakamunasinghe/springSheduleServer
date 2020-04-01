package com.example.springserver.config;

import com.example.springserver.dto.UserResponse;
import com.example.springserver.service.StockDataHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@EnableScheduling
@Configuration
public class SchedulerConfig {
    SimpMessagingTemplate template;

    StockDataHandlerService stockDataHandlerService;

    @Scheduled(fixedDelay = 3000)
    public void sendAddhocMessages() {
        int max = 100;
        int min = 0;
        double value = (Math.random()*((max-min)+1))+min;
        UserResponse userResponse = new UserResponse();
        userResponse.setContent(Double.toString(value));
        template.convertAndSend("/topic/content",  userResponse);
    }

    @Scheduled(fixedDelay = 40000)
    public void sendHttpMessToCreateNewModel() throws IOException {
        stockDataHandlerService = new StockDataHandlerService();
        UserResponse userResponse = new UserResponse();
        userResponse.setContent(stockDataHandlerService.createModel());
        template.convertAndSend("/topic/model",  userResponse);
    }

    @Scheduled(fixedDelay = 3000)
    public void sendHttpMessToGetData() throws IOException {
        stockDataHandlerService = new StockDataHandlerService();
        UserResponse userResponse = new UserResponse();
        userResponse.setContent(stockDataHandlerService.getStockData());
        template.convertAndSend("/topic/data",  userResponse);
    }
}
