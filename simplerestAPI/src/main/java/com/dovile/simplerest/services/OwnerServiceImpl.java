package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.Owner;
import com.dovile.simplerest.entities.OwnerEntity;
import com.dovile.simplerest.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;


    public List<OwnerEntity> findAllOwners() {
        return ownerRepository.findAll();
    }

    public Optional<OwnerEntity> findOwnerById(Integer id) {
        return Optional.empty();
    }


    public OwnerEntity createOwner(Owner owner) {
        OwnerEntity ownerE = new OwnerEntity();
        ownerE.setName(owner.getName());
        return ownerRepository.save(ownerE);
    }


    public void deleteOwnerById(Integer id) {

    }

    public OwnerEntity findOwnerByName(String ownerName) {
        return null;
    }
}
