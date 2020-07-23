package com.dovile.simplerest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "property")
@NamedQueries({
        @NamedQuery(name = "PropertyEntity.findBYType", query = "SELECT p FROM PropertyEntity p WHERE p.type =: property")})

public class PropertyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String type;
    private double tax_rate;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "propertyType")
    private List<BuildingRecordEntity> buildingRecord;

    public PropertyEntity() {
    }

    public PropertyEntity(String type, double tax_rate) {
        this.type = type;
        this.tax_rate = tax_rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<BuildingRecordEntity> getBuildingRecord() {
        return buildingRecord;
    }

    public void setBuildingRecord(List<BuildingRecordEntity> buildingRecord) {
        this.buildingRecord = buildingRecord;
    }

    @Override
    public String toString() {
        return type+" "+tax_rate;
    }
}
