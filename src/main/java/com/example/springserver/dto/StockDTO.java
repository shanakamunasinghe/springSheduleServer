package com.example.springserver.dto;

import java.util.Date;

public class StockDTO {
    private int stock_id;
    private String name;
    private String description;
    private Date created;
    private Date modified;
    private Integer stock_amount;
    private Double stock_price;

    public StockDTO() {
    }

    public int getStock_id() {
        return stock_id;
    }

    public void setStock_id(int stock_id) {
        this.stock_id = stock_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getStock_amount() {
        return stock_amount;
    }

    public void setStock_amount(Integer stock_amount) {
        this.stock_amount = stock_amount;
    }

    public Date getModified() { return modified; }

    public void setModified(Date modified) { this.modified = modified; }

    public Double getStock_price() { return stock_price; }

    public void setStock_price(Double stock_price) { this.stock_price = stock_price; }
}
