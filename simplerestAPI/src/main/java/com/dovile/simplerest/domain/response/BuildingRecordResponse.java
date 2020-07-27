package com.dovile.simplerest.domain.response;

import com.dovile.simplerest.domain.request.BuildingRecordsRequest;

public class BuildingRecordResponse extends BuildingRecordsRequest {

    private Integer id;
    private OwnerResponse owner;
    private PropertyResponse property;

    public BuildingRecordResponse() {
    }

    public BuildingRecordResponse(Integer id, String address, double size, double value, OwnerResponse owner, PropertyResponse property) {
        super(address, size, value);
        this.id = id;
        this.owner = owner;
        this.property = property;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return id + " " + owner + " " + property + " " + super.toString();
    }
}
