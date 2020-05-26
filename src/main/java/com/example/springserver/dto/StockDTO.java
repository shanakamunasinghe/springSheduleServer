package com.example.springserver.dto;

import java.util.Date;

public class StockDTO {
    private int stock_id;
    private String name;
    private String description;
    private Date created;
    private Date modified;
    private Integer stock_amount;

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
}
