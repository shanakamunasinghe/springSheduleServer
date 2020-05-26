package com.example.springserver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int stock_id;
    private String name;
    private String description;
    @JsonBackReference
    @OneToMany(mappedBy = "stock")
    private List<UserStockLog> userStockLogs;
    private Date created;
    private Date modified;
    private Integer stock_amount;


    public Stock() {
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

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public List<UserStockLog> getUserStockLogs() {
        return userStockLogs;
    }

    public void setUserStockLogs(List<UserStockLog> userStockLogs) {
        this.userStockLogs = userStockLogs;
    }

    public Integer getStock_amount() {
        return stock_amount;
    }

    public void setStock_amount(Integer stock_amount) {
        this.stock_amount = stock_amount;
    }
}
