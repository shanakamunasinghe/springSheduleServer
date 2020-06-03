package com.example.springserver.dto;

import java.util.Date;

public class UserStockLogDTO {
    private int user_stock_id;
    private UserDTO userDTO;
    private StockDTO stockDTO;
    private Double price;
    private Integer shares;
    private Double spending;
    private Double profit;
    private Date created;
    private Date modified;

    public UserStockLogDTO() {
    }

    public int getUser_stock_id() {
        return user_stock_id;
    }

    public void setUser_stock_id(int user_stock_id) {
        this.user_stock_id = user_stock_id;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public StockDTO getStockDTO() {
        return this.stockDTO;
    }

    public void setStockDTO(StockDTO stockDTO) {
        this.stockDTO = stockDTO;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getShares() { return shares; }

    public void setShares(Integer shares) { this.shares = shares; }

    public Double getSpending() { return spending; }

    public void setSpending(Double spending) { this.spending = spending; }

    public Double getProfit() { return profit; }

    public void setProfit(Double profit) { this.profit = profit; }
}
