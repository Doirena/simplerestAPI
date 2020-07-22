package com.dovile.simplerest.controllers;

import com.dovile.simplerest.entities.OwnerEntity;
import com.dovile.simplerest.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<OwnerEntity> getAllOwners(){
        return ownerService.findAllOwners();
    }

}
