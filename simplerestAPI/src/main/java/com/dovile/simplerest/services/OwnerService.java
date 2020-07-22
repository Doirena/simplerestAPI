package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.Owner;
import com.dovile.simplerest.entities.OwnerEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author barkauskaite.dovile@gmail.com
 */
public interface OwnerService {

    /**
     * @return list of Owner
     */
    List<OwnerEntity> findAllOwners();

    /**
     * @param id
     * @return Owner by id
     */
    Optional<OwnerEntity> findOwnerById(Integer id);

    /**
     * @param owner
     * @return crete new Owner
     */
    OwnerEntity createOwner(Owner owner);

    /**
     * @param id
     */
    void deleteOwnerById(Integer id);

    /**
     * @param ownerName
     * @return owner by name
     */
    OwnerEntity findOwnerByName(String ownerName);

}
