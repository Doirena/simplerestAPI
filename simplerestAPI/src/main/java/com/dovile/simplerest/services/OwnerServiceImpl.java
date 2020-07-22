package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.request.OwnerRequest;
import com.dovile.simplerest.domain.response.OwnerResponse;
import com.dovile.simplerest.entities.OwnerEntity;
import com.dovile.simplerest.exception.ResourceNotFoundException;
import com.dovile.simplerest.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public List<OwnerResponse> findAllOwners() {
        List<OwnerResponse> ownerList = new ArrayList<OwnerResponse>();
        List<OwnerEntity> ownerEList = ownerRepository.findAll();
        for (int i = 0; i < ownerEList.size(); i++) {
            ownerList.add(new OwnerResponse(ownerEList.get(i).getName()));
        }
        return ownerList;
    }

    public OwnerResponse findOwnerById(Integer id) throws ResourceNotFoundException {
        OwnerResponse ownerResponse = new OwnerResponse();
        OwnerEntity ownerE = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found on: " + id));
        ownerResponse.setName(ResponseEntity.ok().body(ownerE).getBody().getName());
        return ownerResponse;
    }


    public OwnerResponse createOwner(OwnerRequest owner) {
        OwnerEntity ownerE = new OwnerEntity();
        ownerE.setName(owner.getName());
        ownerRepository.save(ownerE);
        OwnerResponse ownerResponse = new OwnerResponse();
        ownerResponse.setName(ownerE.getName());
        return ownerResponse;
    }

    public Map<String, Boolean> deleteOwnerById(Integer id) throws Exception {
        OwnerEntity ownerE = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found on: " + id));
        ownerRepository.delete(ownerE);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public OwnerEntity findOwnerByName(String ownerName) {
        return null;
    }

    public OwnerResponse refurbishOnwer(Integer id, OwnerRequest owner) throws ResourceNotFoundException {
        OwnerEntity ownerE = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found on: " + id));
        ownerE.setName(owner.getName());
        //owner.setBuildingRecords(ownerDetails.getBuildingRecords());
        ownerRepository.save(ownerE);
        OwnerResponse ownerResponse = new OwnerResponse();
        ownerResponse.setName(ResponseEntity.ok(ownerE).getBody().getName());
        return ownerResponse;
    }
}
