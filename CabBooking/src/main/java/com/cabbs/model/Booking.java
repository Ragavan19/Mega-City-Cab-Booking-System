package com.cabbs.model;

import java.sql.Timestamp;

public class Booking {
    private int bookingId;
    private int userId;
    private int cabId;
    private String pickup;
    private String destination;
    private String status; // Default is 'Pending'

    // Constructor
    public Booking(int bookingId, int userId, int cabId, String pickup, String destination, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.cabId = cabId;
        this.pickup = pickup;
        this.destination = destination;
        this.status = status;
    }

    public Booking() {

    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCabId() {
        return cabId;
    }

    public void setCabId(int cabId) {
        this.cabId = cabId;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", userId=" + userId + ", cabId=" + cabId + ", pickup=" + pickup + ", destination=" + destination + ", status=" + status + "]";
    }

    public void setId(int id) {
    }

    public void setBookingTime(Timestamp bookingTime) {
    }
}
