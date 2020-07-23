package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.response.BuildingRecordResponse;
import com.dovile.simplerest.domain.response.OwnerResponse;
import com.dovile.simplerest.domain.response.PropertyResponse;
import com.dovile.simplerest.entities.BuildingRecordEntity;
import com.dovile.simplerest.repositories.BuildingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingRecordServiceImpl implements BuildingRecordService {

    @Autowired
    private BuildingRecordRepository buildingRecordRepository;

    public List<BuildingRecordResponse> findAllRecords() {
        List<BuildingRecordResponse> listRecord = new ArrayList<BuildingRecordResponse>();
        for (BuildingRecordEntity recordE:buildingRecordRepository.findAll()) {
            listRecord.add(new BuildingRecordResponse(recordE.getAddress(),recordE.getSize(),recordE.getValue(),
                   new OwnerResponse(recordE.getOwner().getName()),
                    new PropertyResponse(recordE.getPropertyType().getType(),recordE.getPropertyType().getTax_rate())));
        }
        return listRecord;
    }
}
