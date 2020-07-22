package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.request.OwnerRequest;
import com.dovile.simplerest.domain.response.OwnerResponse;
import com.dovile.simplerest.entities.OwnerEntity;
import com.dovile.simplerest.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Map;


/**
 * @author barkauskaite.dovile@gmail.com
 */
public interface OwnerService {

    /**
     * @return list of Owner
     */
    List<OwnerResponse> findAllOwners();

    /**
     * @param id
     * @return Owner by id
     */
    OwnerResponse findOwnerById(Integer id) throws ResourceNotFoundException;

    /**
     * @param owner
     * @return crete new Owner
     */
    OwnerResponse createOwner(OwnerRequest owner);

    /**
     *
     * @param id
     * @return true/false
     */
    Map<String, Boolean> deleteOwnerById(Integer id) throws Exception;

    /**
     * @param ownerName
     * @return owner by name
     */
    OwnerEntity findOwnerByName(String ownerName);

    /**
     *
     * @param id
     * @param owner
     * @return update Owner
     */
    OwnerResponse refurbishOnwer(Integer id, OwnerRequest owner) throws ResourceNotFoundException;

}
