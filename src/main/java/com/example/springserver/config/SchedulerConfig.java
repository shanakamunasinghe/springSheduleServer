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
    int count = 0;
    boolean enable = true;
//    @Scheduled(fixedDelay = 3000)
//    public void sendAddhocMessages() {
//        int max = 100;
//        int min = 0;
//        double value = (Math.random()*((max-min)+1))+min;
//        UserResponse userResponse = new UserResponse();
//        userResponse.setContent(Double.toString(value));
//        template.convertAndSend("/topic/content",  userResponse);
//    }

    @Scheduled(fixedDelay = 80000)
    public void sendHttpMessToCreateNewModel() throws IOException {
        enable = false;
        stockDataHandlerService = new StockDataHandlerService();
        UserResponse userResponse = new UserResponse();
        for(int co = 0; co <= 1; co++){
            String content = stockDataHandlerService.createModel();
            if(!content.isEmpty()) {
                userResponse.setContent(content);
                enable = true;
                break;
            }
        }
        template.convertAndSend("/topic/model",  userResponse);
    }

    @Scheduled(fixedDelay = 5000)
    public void sendHttpMessToGetData() throws IOException {
        if(enable) {
            stockDataHandlerService = new StockDataHandlerService();
            UserResponse userResponse = new UserResponse();
            String content = stockDataHandlerService.getStockData(count);
            if (!content.isEmpty()) {
                userResponse.setContent(content);
                count++;
            }
            template.convertAndSend("/topic/data", userResponse);
        }
    }
}
