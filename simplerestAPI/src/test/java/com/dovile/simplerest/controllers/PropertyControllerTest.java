package com.dovile.simplerest.controllers;

import com.dovile.simplerest.domain.request.PropertyRequest;
import com.dovile.simplerest.domain.response.PropertyResponse;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
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
public class PropertyControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "/api/properties";
    }

    @Test
    public void getAllProperties_test() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl(),
                HttpMethod.GET, entity, String.class);
        System.out.println(response);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetAllPropValue() throws JSONException {
        String response = restTemplate.getForObject(getRootUrl(), String.class);
        JSONAssert.assertEquals("[{\"tax_rate\":20.5},{\"tax_rate\":50.0},{\"tax_rate\":65.2},{\"tax_rate\":75.75}]", response, false);
    }

    @Test
    public void testGetOnePropValue() throws JSONException {
        String response = restTemplate.getForObject(getRootUrl() + "/3", String.class);
        JSONAssert.assertEquals("{\"type\":\"Apartment\"}", response, false);
    }

    @Test
    public void getPropertyByID_test() {
        PropertyResponse property = restTemplate.getForObject(getRootUrl() + "/1", PropertyResponse.class);
        assertNotNull(property);
    }

    @Test
    public void addNewProperty_test() {
        PropertyRequest property = new PropertyRequest();
        property.setType("Flat");
        property.setTax_rate(20.5);
        ResponseEntity<PropertyResponse> postResponse = restTemplate.postForEntity(getRootUrl() + "/property", property, PropertyResponse.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void updateProperty_test() {
        Integer id = 1;
        PropertyResponse propertyResponse = restTemplate.getForObject(getRootUrl() + "/" + id, PropertyResponse.class);
        PropertyRequest property = new PropertyRequest();
        property.setType("House");
        restTemplate.put(getRootUrl() + "/" + id, property);
        PropertyResponse updatedProperty = restTemplate.getForObject(getRootUrl() + "/" + id, PropertyResponse.class);
        assertEquals(property.getType(), updatedProperty.getType());
        assertNotNull(updatedProperty);
    }

    @Test
    public void deletePropperty_test() {
        Integer id = 4;
        PropertyResponse property = restTemplate.getForObject(getRootUrl() + "/" + id, PropertyResponse.class);
        assertNotNull(property);
        restTemplate.delete(getRootUrl() + "/" + id);
        try {
            property = restTemplate.getForObject(getRootUrl() + "/" + id, PropertyResponse.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}