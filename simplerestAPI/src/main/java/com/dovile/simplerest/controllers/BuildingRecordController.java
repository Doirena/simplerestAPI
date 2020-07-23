package com.dovile.simplerest.controllers;

import com.dovile.simplerest.domain.request.BuildingRecordsRequest;
import com.dovile.simplerest.domain.response.BuildingRecordResponse;
import com.dovile.simplerest.exception.ResourceNotFoundException;
import com.dovile.simplerest.services.BuildingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/records")
public class BuildingRecordController {
    @Autowired
    private BuildingRecordService buildingRecordService;

    @GetMapping
    public List<BuildingRecordResponse> findAllRecords() {
        return buildingRecordService.findAllRecords();
    }

    @PostMapping(value = "/record", params = {"owner", "property"})
    public BuildingRecordResponse createRecord(
            @RequestBody BuildingRecordsRequest record,
            @RequestParam(value = "owner") String owner,
            @RequestParam(value = "property") String property) {
        return buildingRecordService.createRecord(record, owner, property);
    }

    @PutMapping("/{id}")
    public BuildingRecordResponse updateRecord(@PathVariable(value = "id") Integer recordId,
                                               @RequestBody @Valid BuildingRecordsRequest record)
            throws ResourceNotFoundException {
        return buildingRecordService.refurbishRecord(recordId, record);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRecord(@PathVariable(value = "id") Integer recordId) throws Exception {
        return buildingRecordService.deleteRecord(recordId);
    }
}
