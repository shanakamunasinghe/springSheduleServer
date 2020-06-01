package com.example.springserver.service;

import com.example.springserver.dto.StockSocketResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class StockDataHandlerService {
    CloseableHttpClient client;
    private static final String GET_URL = "http://localhost:8000/stock/";
    HttpGet httpGet;

    public StockDataHandlerService() {
        client = HttpClients.createDefault();
    }

    public String getStockData(int data) throws IOException {
        String value = Integer.toString(data);
        httpGet  = new HttpGet(GET_URL+"getStockPrediction/"+value);
        CloseableHttpResponse httpResponse = client.execute(httpGet);
        System.out.println(httpResponse.getStatusLine().getStatusCode());
        HttpEntity entity = httpResponse.getEntity();
        // Read the contents of an entity and return it as a String.
        String content = EntityUtils.toString(entity);
        httpResponse.close();
        client.close();
        return content;
    }

    public String createModel() throws IOException {
        httpGet  = new HttpGet(GET_URL+"startModel");
        CloseableHttpResponse httpResponse = client.execute(httpGet);
        System.out.println(httpResponse.getStatusLine().getStatusCode());
        HttpEntity entity = httpResponse.getEntity();
        // Read the contents of an entity and return it as a String.
        String content = EntityUtils.toString(entity);
        httpResponse.close();
        client.close();
        return content;
    }


}
