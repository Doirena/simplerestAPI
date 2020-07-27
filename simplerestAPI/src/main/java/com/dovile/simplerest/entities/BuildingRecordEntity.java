package com.dovile.simplerest.entities;

import javax.persistence.*;

@Entity
@Table(name = "building_records")
@NamedQueries(value = {
        @NamedQuery(name = "BuildingRecordEntity.RealEstateTaxes", query = "SELECT SUM(b.value*(p.tax_rate/100)) FROM BuildingRecordEntity b join b.owner o join b.propertyType p WHERE (o.id = :id)")})

public class BuildingRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;
    private double size;
    private double value;
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private OwnerEntity owner;

    @JoinColumn(name = "property_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PropertyEntity propertyType;

    public BuildingRecordEntity() {
    }

    public BuildingRecordEntity(Integer id, String address, double size, double value, OwnerEntity owner,
                                PropertyEntity propertyType) {
        this.id = id;
        this.address = address;
        this.size = size;
        this.value = value;
        this.owner = owner;
        this.propertyType = propertyType;
    }
    public BuildingRecordEntity(Integer id, String address, double size, double value) {
        this.id = id;
        this.address = address;
        this.size = size;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public OwnerEntity getOwner() {
        return owner;
    }

    public void setOwner(OwnerEntity owner) {
        this.owner = owner;
    }

    public PropertyEntity getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyEntity propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public String toString() {
        return address +" " + size +" " + value +" " + owner + " " + propertyType;
    }
}
