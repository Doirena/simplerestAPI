package com.dovile.simplerest.domain.request;

public class BuildingRecordsRequest {

    private String address;
    private double size;
    private double value;

    public BuildingRecordsRequest() {
    }

    public BuildingRecordsRequest(String address, double size, double value) {
        this.address = address;
        this.size = size;
        this.value = value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return address + " " + size + " " + value;
    }
}
