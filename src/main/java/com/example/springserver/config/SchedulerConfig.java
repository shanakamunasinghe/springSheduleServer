package com.example.springserver.config;

import com.example.springserver.dto.PredictStockDTO;
import com.example.springserver.dto.StockSocketResponse;
import com.example.springserver.dto.UserResponse;
import com.example.springserver.service.StockDataHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;

@EnableScheduling
@Configuration
public class SchedulerConfig {
    @Autowired
    SimpMessagingTemplate template;
    @Autowired
    StockDataHandlerService stockDataHandlerService;
    int count = 0;
    boolean enable = true;
    String line = "";
    String cvsSplitBy = ",";
    HashMap<Integer, String> csvList = new HashMap<Integer, String>();
    HashMap<Integer, Integer> fileNo = new HashMap<Integer, Integer>();

//    @Scheduled(fixedDelay = 3000)
//    public void sendAddhocMessages() {
//        int max = 100;
//        int min = 0;
//        double value = (Math.random()*((max-min)+1))+min;
//        UserResponse userResponse = new UserResponse();
//        userResponse.setContent(Double.toString(value));
//        template.convertAndSend("/topic/content",  userResponse);
//    }

//    @Scheduled(fixedDelay = 80000)
//    public void sendHttpMessToCreateNewModel() throws IOException {
//
//        stockDataHandlerService = new StockDataHandlerService();
//        UserResponse stockSocketResponse = new UserResponse();
//        for (int co = 0; co <= 1; co++) {
//            String content = stockDataHandlerService.createModel();
//            if (!content.isEmpty()) {
//                stockSocketResponse.setContent(content);
//                enable = true;
//                break;
//            }
//        }
////        template.convertAndSend("/topic/model", stockSocketResponse);
//    }


    public SchedulerConfig() {
        String csvFile1 = "C:\\Users\\shanaka\\Desktop\\new\\springServer\\src\\main\\resources\\formatted_all.csv";
        String csvFile2 = "C:\\Users\\shanaka\\Desktop\\new\\springServer\\src\\main\\resources\\formatted_128714-JKH.csv";
        String csvFile3 = "C:\\Users\\shanaka\\Desktop\\new\\springServer\\src\\main\\resources\\formatted_128714-JKH.csv";
        //formatted_128714-JKH
        csvList.put(2,csvFile1);
        csvList.put(3,csvFile2);
        csvList.put(4,csvFile3);

        fileNo.put(2,1349);
        fileNo.put(3,1525);
        fileNo.put(4,1425);
    }

    @Scheduled(fixedDelay = 5000)
    public void sendHttpMessToGetData() throws IOException {
        stockDataHandlerService = new StockDataHandlerService();
        List<PredictStockDTO> predictStockDTOS = new ArrayList<>();

        StockSocketResponse stockSocketResponse = new StockSocketResponse();
        if (enable) {
            predictStockDTOS = stockDataHandlerService.getStockData(count);


            if (!predictStockDTOS.isEmpty()) {
                csvReader(count, stockSocketResponse, predictStockDTOS.get(0).getId());
                stockSocketResponse.setPredict(predictStockDTOS.get(0).getPredict());
                template.convertAndSend("/topic/stockData/" + predictStockDTOS.get(0).getId(), stockSocketResponse);
// ------------------------ csv  reader ---------
                stockSocketResponse.setPredict(predictStockDTOS.get(1).getPredict());
                template.convertAndSend("/topic/stockData/"+ predictStockDTOS.get(1).getId(), stockSocketResponse);
                stockSocketResponse.setPredict(predictStockDTOS.get(2).getPredict());
                template.convertAndSend("/topic/stockData/"+ predictStockDTOS.get(2).getId(), stockSocketResponse);
                count++;
            }
        }



    }


    public StockSocketResponse csvReader(int value, StockSocketResponse stockSocketResponse, int stockId) {
        int countValue = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvList.get(stockId)))) {
            while ((line = br.readLine()) != null || countValue > fileNo.get(stockId) + value) {
                if (countValue == fileNo.get(stockId) + value) {
                    String[] stockCSV = line.split(cvsSplitBy);
                    stockSocketResponse.setDate(new Date());
                    stockSocketResponse.setOpen(Double.parseDouble(stockCSV[2]));
                    stockSocketResponse.setHigh(Double.parseDouble(stockCSV[3]));
                    stockSocketResponse.setLow(Double.parseDouble(stockCSV[4]));
                    stockSocketResponse.setClose(Double.parseDouble(stockCSV[5]));
                    stockSocketResponse.setStockId(stockId);
                    System.out.println(stockCSV[3]);
                    break;
                }
                countValue++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockSocketResponse;
    }
}

