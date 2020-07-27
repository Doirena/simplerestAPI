package com.dovile.simplerest.domain.response;

import com.dovile.simplerest.domain.request.PropertyRequest;

public class PropertyResponse extends PropertyRequest {

    private Integer id;

    public PropertyResponse() {
    }

    public PropertyResponse(Integer id, String type, double tax_rate) {
        super(type, tax_rate);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id+" "+ super.toString();
    }
}
