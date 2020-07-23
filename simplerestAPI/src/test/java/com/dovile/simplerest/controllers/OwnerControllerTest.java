package com.dovile.simplerest.controllers;

import com.dovile.simplerest.domain.request.OwnerRequest;
import com.dovile.simplerest.domain.response.OwnerResponse;
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
public class OwnerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "/api/owners";
    }

    @Test
    public void getAllOwners_test() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl(),
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void getOwnerById_test() {
        OwnerResponse owner= restTemplate.getForObject(getRootUrl() + "/1", OwnerResponse.class);
        assertEquals("Anna", owner.getName());
        assertNotNull(owner);
    }

    @Test
    public void createOwner_test() {
        OwnerRequest owner = new OwnerRequest();
        owner.setName("Dovile");
        ResponseEntity<OwnerResponse> postResponse = restTemplate.postForEntity(getRootUrl() + "/owner", owner,
                OwnerResponse.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void updateOwner() {
        Integer id = 1;
        OwnerResponse ownerResponse = restTemplate.getForObject(getRootUrl() + "/" + id, OwnerResponse.class);
        OwnerRequest owner = new OwnerRequest();
        owner.setName("Dovile");
        restTemplate.put(getRootUrl() + "/" + id, owner);
        OwnerResponse updatedOwner = restTemplate.getForObject(getRootUrl() +"/" + id, OwnerResponse.class);
        assertEquals(owner.getName(),updatedOwner.getName());
        assertNotNull(updatedOwner);
    }

    @Test
    public void deleteOwner_test() {
        Integer id = 2;
        OwnerResponse owner = restTemplate.getForObject(getRootUrl() + "/" + id, OwnerResponse.class);
        assertNotNull(owner);
        restTemplate.delete(getRootUrl() + "/" + id);
        try {
            owner = restTemplate.getForObject(getRootUrl() + "/" + id, OwnerResponse.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}