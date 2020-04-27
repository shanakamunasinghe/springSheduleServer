package com.example.springserver.model;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users_stocks_log")
public class UserStockLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_stock_id;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Stock stock;
    private Integer shares;
    private Double amount;
    private Date created;
    private Date modified;

    public UserStockLog() {
    }

    public int getUser_stock_id() {
        return user_stock_id;
    }

    public void setUser_stock_id(int user_stock_id) {
        this.user_stock_id = user_stock_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Stock getStock() { return stock; }

    public void setStock(Stock stock) {
        this.stock = stock;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getShares() { return shares; }

    public void setShares(Integer shares) { this.shares = shares; }
}
