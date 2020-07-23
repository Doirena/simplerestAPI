package com.dovile.simplerest.controllers;

import com.dovile.simplerest.domain.response.BuildingRecordResponse;
import com.dovile.simplerest.entities.BuildingRecordEntity;
import com.dovile.simplerest.services.BuildingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class BuildingRecordController {
    @Autowired
    private BuildingRecordService buildingRecordService;

    @GetMapping
    public List<BuildingRecordResponse> findAllRecords(){
        return buildingRecordService.findAllRecords();
    }
}
