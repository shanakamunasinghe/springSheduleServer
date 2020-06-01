package com.example.springserver.config;

import com.example.springserver.dto.StockSocketResponse;
import com.example.springserver.dto.UserResponse;
import com.example.springserver.service.StockDataHandlerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Date;

@EnableScheduling
@Configuration
public class SchedulerConfig {
    SimpMessagingTemplate template;

    StockDataHandlerService stockDataHandlerService;
    int count = 0;
    boolean enable = true;
    String csvFile = "C:\\Users\\shanaka\\Desktop\\new\\springServer\\src\\main\\resources\\formatted_all.csv";
    String line = "";
    String cvsSplitBy = ",";

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
        UserResponse stockSocketResponse = new UserResponse();
        for (int co = 0; co <= 1; co++) {
            String content = stockDataHandlerService.createModel();
            if (!content.isEmpty()) {
                stockSocketResponse.setContent(content);
                enable = true;
                break;
            }
        }
//        template.convertAndSend("/topic/model", stockSocketResponse);
    }

    @Scheduled(fixedDelay = 5000)
    public void sendHttpMessToGetData() throws IOException {
        stockDataHandlerService = new StockDataHandlerService();
        StockSocketResponse stockSocketResponse = new StockSocketResponse();
        if (enable) {
            String content = stockDataHandlerService.getStockData(count);
            stockSocketResponse = csvReader(count);
            stockSocketResponse.setPredict(content);
            count++;
        }
//        template.convertAndSend("/topic/data", stockSocketResponse);

    }



    public StockSocketResponse csvReader(int value) {
        StockSocketResponse stockSocketResponse = new StockSocketResponse();
        count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null || count > 1349 + value) {
                if (count == 1349 + value) {
                    String[] stockCSV = line.split(cvsSplitBy);
                    stockSocketResponse.setDate(new Date());
                    stockSocketResponse.setHigh(Double.parseDouble(stockCSV[3]));
                    stockSocketResponse.setLow(Double.parseDouble(stockCSV[4]));
                    stockSocketResponse.setPrice(Double.parseDouble(stockCSV[3]));
                    stockSocketResponse.setStockId(2);
                    System.out.println(stockCSV[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockSocketResponse;
    }
}

