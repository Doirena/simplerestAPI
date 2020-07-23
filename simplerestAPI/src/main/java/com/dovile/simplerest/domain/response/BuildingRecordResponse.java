package com.dovile.simplerest.domain.response;

public class BuildingRecordResponse {
    private String address;
    private double size;
    private double value;
    private OwnerResponse owner;
    private PropertyResponse property;

    public BuildingRecordResponse() {
    }

    public BuildingRecordResponse(String address, double size, double value,
                                  OwnerResponse owner, PropertyResponse property) {
        this.address = address;
        this.size = size;
        this.value = value;
        this.owner = owner;
        this.property = property;
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

    public OwnerResponse getOwner() {
        return owner;
    }

    public void setOwner(OwnerResponse owner) {
        this.owner = owner;
    }

    public PropertyResponse getProperty() {
        return property;
    }

    public void setProperty(PropertyResponse property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "BuildingRecordResponse{" +
                "address='" + address + '\'' +
                ", size=" + size +
                ", value=" + value +
                ", owner=" + owner +
                ", property=" + property +
                '}';
    }
}
