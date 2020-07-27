package com.dovile.simplerest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "owner")
@NamedQueries({
        @NamedQuery(name = "OwnerEntity.findBYName", query = "SELECT o FROM OwnerEntity o WHERE o.name =: owner")})
public class OwnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<BuildingRecordEntity> buildingRecord;

    public OwnerEntity() {
    }

    public OwnerEntity(String name) {
        this.name = name;
    }
    public OwnerEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BuildingRecordEntity> getBuildingRecord() {
        return buildingRecord;
    }

    public void setBuildingRecord(List<BuildingRecordEntity> buildingRecord) {
        this.buildingRecord = buildingRecord;
    }

    @Override
    public String toString() {
        return  id+" "+name;
    }
}
