package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.request.BuildingRecordsRequest;
import com.dovile.simplerest.domain.response.BuildingRecordResponse;
import com.dovile.simplerest.entities.BuildingRecordEntity;
import com.dovile.simplerest.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author barkauskaite.dovile@gmail.com
 */
public interface BuildingRecordService {

    /**
     *
     * @return list of building records
     */
    List<BuildingRecordResponse> findAllRecords();

    /**
     *
     * @param record
     * @param owner
     * @param property
     * @return new Building Record
     */
    BuildingRecordResponse createRecord(BuildingRecordsRequest record, String owner, String property);

    /**
     *
     * @param id
     * @param record
     * @return update Building Record
     * @throws ResourceNotFoundException
     */
    BuildingRecordResponse refurbishRecord(Integer id, BuildingRecordsRequest record)
            throws ResourceNotFoundException;
}
