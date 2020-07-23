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
        for (OwnerEntity ownerE : ownerRepository.findAll()) {
            ownerList.add(new OwnerResponse(ownerE.getName()));
        }
        return ownerList;
    }

    public OwnerResponse findOwnerById(Integer id) throws ResourceNotFoundException {
        OwnerEntity ownerE = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found on: " + id));
        return new OwnerResponse(ResponseEntity.ok().body(ownerE).getBody().getName());
    }


    public OwnerResponse createOwner(OwnerRequest owner) {
        OwnerEntity ownerE = new OwnerEntity();
        ownerE.setName(owner.getName());
        ownerRepository.save(ownerE);
        return new OwnerResponse(ownerE.getName());
    }

    public Map<String, Boolean> deleteOwnerById(Integer id) throws Exception {
        OwnerEntity ownerE = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found on: " + id));
        ownerRepository.delete(ownerE);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public OwnerResponse refurbishOwner(Integer id, OwnerRequest owner) throws ResourceNotFoundException {
        OwnerEntity ownerE = ownerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Owner not found on: " + id));
        ownerE.setName(owner.getName());
        //ownerE.setBuildingRecord(owner.);
        ownerRepository.save(ownerE);
        return new OwnerResponse(ResponseEntity.ok(ownerE).getBody().getName());
    }
}
