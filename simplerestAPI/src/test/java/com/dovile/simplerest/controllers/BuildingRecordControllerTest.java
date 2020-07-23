package com.dovile.simplerest.controllers;

import com.dovile.simplerest.domain.request.BuildingRecordsRequest;
import com.dovile.simplerest.domain.response.BuildingRecordResponse;
import com.dovile.simplerest.entities.BuildingRecordEntity;
import com.dovile.simplerest.entities.OwnerEntity;
import com.dovile.simplerest.entities.PropertyEntity;
import com.dovile.simplerest.exception.ResourceNotFoundException;
import com.dovile.simplerest.services.BuildingRecordService;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuildingRecordControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BuildingRecordService buildingRecordService;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "/api/records";
    }

    @Test
    public void getAllRecords_test() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl(),
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void createRecord_test() {
        BuildingRecordsRequest record = new BuildingRecordsRequest();
        record.setAddress("Naugagardukas str. 1, Vilnius");
        record.setSize(200);
        record.setValue(30);
        ResponseEntity<BuildingRecordResponse> postResponse = (ResponseEntity<BuildingRecordResponse>)
                restTemplate.postForEntity(getRootUrl() + "/record?owner=Anna&property=Flat",
                        record, BuildingRecordResponse.class);
        System.out.println(postResponse);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(postResponse.getBody().getOwner().getName(), "Anna");
        assertEquals(postResponse.getBody().getProperty().getType(), "Flat");
    }

    @Test
    public void getTaxes_test() throws JSONException {
        Integer id = 3;
        String reponse = this.restTemplate.getForObject(getRootUrl() + "/taxes/" + id, String.class);
        System.out.println(reponse);
        assertEquals("4.65", reponse);
    }

    @Test
    public void updateRecord_test() throws ResourceNotFoundException {
        Integer id = 1;
        BuildingRecordsRequest record = new BuildingRecordsRequest();
        record.setAddress("Laisves al. Kaunas");
        record.setValue(20);
        record.setSize(6);
        restTemplate.put(getRootUrl() + "/" + id, record);
        BuildingRecordResponse updatedRecord = buildingRecordService.getOneRecord(id);
        assertNotNull(updatedRecord);
        assertEquals("Laisves al. Kaunas", updatedRecord.getAddress());
    }
    @Test
    public void deleteRecord_test() throws ResourceNotFoundException {
        Integer id = 1;
        BuildingRecordResponse record = buildingRecordService.getOneRecord(id);
        assertNotNull(record);
        System.out.println(record);
        restTemplate.delete(getRootUrl() + "/" + id);
        try {
            record = buildingRecordService.getOneRecord(id);
        } catch (final HttpClientErrorException | ResourceNotFoundException e) {
            assertEquals(e.getMessage(), "Record not found on: 1");
        }
    }
}