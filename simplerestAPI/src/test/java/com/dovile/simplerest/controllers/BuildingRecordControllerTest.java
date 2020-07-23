package com.dovile.simplerest.controllers;

import com.dovile.simplerest.domain.request.BuildingRecordsRequest;
import com.dovile.simplerest.domain.response.BuildingRecordResponse;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BuildingRecordControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

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
    public void createRecord_test(){
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
    public void GetTaxes_test() throws JSONException {
        Integer id = 3;
        String reponse = this.restTemplate.getForObject(getRootUrl()+"/taxes/" + id, String.class);
        System.out.println(reponse);
        assertEquals("4.65", reponse);
    }
}