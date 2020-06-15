package com.example.springserver.dto;

public class PredictStockDTO {
    private int id;
    private String predict;

    public PredictStockDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPredict() {
        return predict;
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }
}
