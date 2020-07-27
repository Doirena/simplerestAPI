package com.dovile.simplerest.domain.response;

import com.dovile.simplerest.domain.request.OwnerRequest;

public class OwnerResponse extends OwnerRequest {

    private Integer id;

    public OwnerResponse(Integer id, String name) {
        super(name);
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
        return id+" " + super.toString();
    }
}
