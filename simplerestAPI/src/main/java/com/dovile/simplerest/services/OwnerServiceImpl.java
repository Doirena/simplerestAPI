package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.request.OwnerRequest;
import com.dovile.simplerest.domain.response.OwnerResponse;
import com.dovile.simplerest.entities.OwnerEntity;
import com.dovile.simplerest.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;


    public List<OwnerResponse> findAllOwners() {
       List<OwnerResponse>ownerList = new ArrayList<OwnerResponse>();
       List<OwnerEntity> ownerEList = ownerRepository.findAll();
       for (int i=0; i<ownerEList.size(); i++){
           ownerList.add(new OwnerResponse(ownerEList.get(i).getName()));
       }
        return ownerList;
    }

    public Optional<OwnerEntity> findOwnerById(Integer id) {
        return Optional.empty();
    }


    public OwnerResponse createOwner(OwnerRequest owner) {
        OwnerEntity ownerE =new OwnerEntity();
        ownerE.setName(owner.getName());
        ownerRepository.save(ownerE);
        OwnerResponse ownerResponse=new OwnerResponse();
        ownerResponse.setName(ownerE.getName());
        return ownerResponse;
    }


    public void deleteOwnerById(Integer id) {

    }

    public OwnerEntity findOwnerByName(String ownerName) {
        return null;
    }
}
