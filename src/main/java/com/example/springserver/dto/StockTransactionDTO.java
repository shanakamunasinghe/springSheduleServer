package com.example.springserver.dto;

public class StockTransactionDTO {
    private int user_id;
    private int stock_id;
    private int stock_shares;
    private Double stock_price;
    private int buyOrSell;

    public StockTransactionDTO() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public int getStock_shares() {
        return stock_shares;
    }

    public void setStock_shares(int stock_shares) {
        this.stock_shares = stock_shares;
    }

    public Double getStock_price() {
        return stock_price;
    }

    public void setStock_price(Double stock_price) {
        this.stock_price = stock_price;
    }

    public int getBuyOrSell() {
        return buyOrSell;
    }

    public void setBuyOrSell(int buyOrSell) {
        this.buyOrSell = buyOrSell;
    }
}
