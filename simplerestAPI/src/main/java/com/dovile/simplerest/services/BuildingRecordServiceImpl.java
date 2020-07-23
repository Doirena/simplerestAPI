package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.request.BuildingRecordsRequest;
import com.dovile.simplerest.domain.response.BuildingRecordResponse;
import com.dovile.simplerest.domain.response.OwnerResponse;
import com.dovile.simplerest.domain.response.PropertyResponse;
import com.dovile.simplerest.entities.BuildingRecordEntity;
import com.dovile.simplerest.entities.OwnerEntity;
import com.dovile.simplerest.entities.PropertyEntity;
import com.dovile.simplerest.repositories.BuildingRecordRepository;
import com.dovile.simplerest.repositories.OwnerRepository;
import com.dovile.simplerest.repositories.PropertyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingRecordServiceImpl implements BuildingRecordService {

    @Autowired
    private BuildingRecordRepository buildingRecordRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PropertyRespository propertyRespository;

    public List<BuildingRecordResponse> findAllRecords() {
        List<BuildingRecordResponse> listRecords = new ArrayList<BuildingRecordResponse>();
        for (BuildingRecordEntity recordE : buildingRecordRepository.findAll()) {
            listRecords.add(new BuildingRecordResponse(recordE.getAddress(), recordE.getSize(), recordE.getValue(),
                    new OwnerResponse(recordE.getOwner().getName()),
                    new PropertyResponse(recordE.getPropertyType().getType(), recordE.getPropertyType().getTax_rate())));
        }
        return listRecords;
    }

    public BuildingRecordResponse createRecord(BuildingRecordsRequest record, String owner, String property) {
        BuildingRecordEntity recordE = new BuildingRecordEntity();
        PropertyEntity propertyE = propertyRespository.findBYType(property);
        if (propertyE != null) {
            recordE.setPropertyType(propertyE);
        } else {
            throw new ArithmeticException("Please insert a correct Property Type");
        }
        OwnerEntity ownerE = ownerRepository.findBYName(owner);
        if (ownerE != null) {
            recordE.setOwner(ownerE);
        } else {
            throw new ArithmeticException("Please insert a correct Owner");
        }
        recordE.setAddress(record.getAddress());
        recordE.setSize(record.getSize());
        recordE.setValue(record.getValue());
        buildingRecordRepository.save(recordE);
        return new BuildingRecordResponse(recordE.getAddress(), recordE.getSize(), recordE.getValue(),
                new OwnerResponse(recordE.getOwner().getName()),
                new PropertyResponse(recordE.getPropertyType().getType(), recordE.getPropertyType().getTax_rate()));
    }
}
