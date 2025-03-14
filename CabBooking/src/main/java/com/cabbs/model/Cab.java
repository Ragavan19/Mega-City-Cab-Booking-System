package com.cabbs.model;

public class Cab {
    private int cabId;
    private String model;
    private int capacity;
    private String status;
    private Integer driverId; // Using Integer to allow for NULL values

    // Constructor
    public Cab(int cabId, String model) {
        this.cabId = cabId;
        this.model = model;
        this.capacity = capacity;
        this.status = status;
        this.driverId = driverId;
    }

    public Cab(int cabId, String model, String licensePlate) {
    }

    // Getters and Setters
    public int getCabId() {
        return cabId;
    }

    public void setCabId(int cabId) {
        this.cabId = cabId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    @Override
    public String toString() {
        return "Cab [cabId=" + cabId + ", model=" + model + ", capacity=" + capacity + ", status=" + status + ", driverId=" + driverId + "]";
    }
}
