package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.request.PropertyRequest;
import com.dovile.simplerest.domain.response.PropertyResponse;
import com.dovile.simplerest.entities.PropertyEntity;
import com.dovile.simplerest.exception.ResourceNotFoundException;
import com.dovile.simplerest.repositories.PropertyRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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


    public PropertyResponse findPropertyById(Integer id) throws ResourceNotFoundException {
        PropertyEntity propertyE = propertyRespository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found on: " + id));
        PropertyResponse propertyResponse = new PropertyResponse(ResponseEntity.ok().body(propertyE).getBody().getType(),
                ResponseEntity.ok().body(propertyE).getBody().getTax_rate());
        return propertyResponse;
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
