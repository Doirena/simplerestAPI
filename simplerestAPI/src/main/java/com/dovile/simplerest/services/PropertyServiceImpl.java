package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.request.PropertyRequest;
import com.dovile.simplerest.domain.response.PropertyResponse;
import com.dovile.simplerest.entities.PropertyEntity;
import com.dovile.simplerest.repositories.PropertyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRespository propertyRespository;

    public List<PropertyResponse> findAllProperties() {
        List<PropertyResponse> listProperties = new ArrayList<PropertyResponse>();
        for (PropertyEntity propertyE:propertyRespository.findAll()) {
            listProperties.add(new PropertyResponse(propertyE.getType(),propertyE.getTax_rate()));
        }
        return listProperties;
    }

    @Override
    public PropertyResponse findPropertyById(Integer id) {
        return null;
    }

    @Override
    public PropertyResponse createProperty(PropertyRequest property) {
        return null;
    }

    @Override
    public PropertyResponse refurbishProperty(Integer id, PropertyRequest property) {
        return null;
    }

    @Override
    public Map<String, Boolean> deleteProperty(Integer id) {
        return null;
    }
}
