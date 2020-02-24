package com.example.springserver.config;

import com.example.springserver.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class SchedulerConfig {
    @Autowired
    SimpMessagingTemplate template;

    @Scheduled(fixedDelay = 3000)
    public void sendAdhocMessages() {
        int max = 100;
        int min = 0;
        double value = (Math.random()*((max-min)+1))+min;
        UserResponse userResponse = new UserResponse();
        userResponse.setContent(Double.toString(value));
        template.convertAndSend("/topic/content",  userResponse);
    }
}
