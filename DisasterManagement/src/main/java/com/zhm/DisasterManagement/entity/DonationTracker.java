package com.zhm.DisasterManagement.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "donation_tracker")
public class DonationTracker {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String contactNumber;
    private double amount;
    private Date donationDate;

    // constructors

    public DonationTracker() {}

    public DonationTracker(String name, String email, String contactNumber, double amount, Date donationDate) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.amount = amount;
        this.donationDate = donationDate;
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

    // toString() method

    @Override
    public String toString() {
        return "DonationTracker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", amount=" + amount +
                ", donationDate=" + donationDate +
                '}';
    }
}
