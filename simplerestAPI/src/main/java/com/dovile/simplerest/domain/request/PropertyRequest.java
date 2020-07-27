package com.dovile.simplerest.domain.request;

public class PropertyRequest {
    private String type;
    private double tax_rate;

    public PropertyRequest() {
    }

    public PropertyRequest(String type, double tax_rate) {
        this.type = type;
        this.tax_rate = tax_rate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTax_rate() {
        return tax_rate;
    }

    public void setTax_rate(double tax_rate) {
        this.tax_rate = tax_rate;
    }

    @Override
    public String toString() {
        return type +" "+ tax_rate;
    }
}
