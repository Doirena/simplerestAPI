package com.dovile.simplerest.domain.response;

public class OwnerResponse {

    private String name;

    public OwnerResponse() {

    }
    public OwnerResponse(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OwnerResponse{" +
                "name='" + name + '\'' +
                '}';
    }
}
