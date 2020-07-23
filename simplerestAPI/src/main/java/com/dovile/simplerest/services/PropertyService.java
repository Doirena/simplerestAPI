package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.request.PropertyRequest;
import com.dovile.simplerest.domain.response.PropertyResponse;

import java.util.List;
import java.util.Map;

/**
 * @author barkauskaite.dovile@gmail.com
 */
public interface PropertyService {

    /**
     * @return List of properties
     */
    List<PropertyResponse> findAllProperties();

    /**
     * @param id
     * @return Property by id
     */
    PropertyResponse findPropertyById(Integer id);

    /**
     *
     * @param property
     * @return new Property
     */
    PropertyResponse createProperty(PropertyRequest property);

    /**
     *
     * @param id
     * @param property
     * @return update Property
     */
    PropertyResponse refurbishProperty(Integer id, PropertyRequest property);

    /**
     *
     * @param id
     * @return true/false when delete Property
     */
    Map<String, Boolean> deleteProperty(Integer id);


}
