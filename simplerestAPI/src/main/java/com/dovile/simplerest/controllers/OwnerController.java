package com.dovile.simplerest.controllers;

import com.dovile.simplerest.domain.request.OwnerRequest;
import com.dovile.simplerest.domain.response.OwnerResponse;
import com.dovile.simplerest.entities.OwnerEntity;
import com.dovile.simplerest.exception.ResourceNotFoundException;
import com.dovile.simplerest.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<OwnerResponse> getAllOwners() {
        return ownerService.findAllOwners();
    }

    @GetMapping("/{id}")
    public OwnerResponse getOwnerById(@PathVariable(value = "id") Integer ownerId)
            throws ResourceNotFoundException {
        return ownerService.findOwnerById(ownerId);
    }

    @PostMapping("/addOwner")
    public OwnerResponse createOwner(@RequestBody @Valid OwnerRequest owner) {
        return ownerService.createOwner(owner);
    }

}
