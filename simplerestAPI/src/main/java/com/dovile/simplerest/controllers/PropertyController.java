package com.dovile.simplerest.controllers;

import com.dovile.simplerest.domain.response.PropertyResponse;
import com.dovile.simplerest.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @GetMapping
    public List<PropertyResponse> getAllProperties(){
        return propertyService.findAllProperties();
    }
}
