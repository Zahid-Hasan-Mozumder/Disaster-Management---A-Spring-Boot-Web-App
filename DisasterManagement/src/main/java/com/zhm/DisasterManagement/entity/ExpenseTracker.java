package com.zhm.DisasterManagement.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "expense_tracker")
public class ExpenseTracker {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String memo;
    private double total;
    private String purpose;
    private Date purchaseDate;
    private String purchasedBy;

    // constructors
    public ExpenseTracker() {}

    public ExpenseTracker(String memo, double total, String purpose, Date purchaseDate, String purchasedBy) {
        this.memo = memo;
        this.total = total;
        this.purpose = purpose;
        this.purchaseDate = purchaseDate;
        this.purchasedBy = purchasedBy;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchasedBy() {
        return purchasedBy;
    }

    public void setPurchasedBy(String purchasedBy) {
        this.purchasedBy = purchasedBy;
    }

    // toString() method

    @Override
    public String toString() {
        return "ExpenseTracker{" +
                "id=" + id +
                ", memo='" + memo + '\'' +
                ", total=" + total +
                ", purpose='" + purpose + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", purchasedBy='" + purchasedBy + '\'' +
                '}';
    }
}
