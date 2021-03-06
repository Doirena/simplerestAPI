package com.dovile.simplerest.moctest;

import com.dovile.simplerest.controllers.BuildingRecordController;
import com.dovile.simplerest.domain.request.BuildingRecordsRequest;
import com.dovile.simplerest.domain.response.BuildingRecordResponse;
import com.dovile.simplerest.domain.response.OwnerResponse;
import com.dovile.simplerest.domain.response.PropertyResponse;
import com.dovile.simplerest.services.BuildingRecordService;
import com.dovile.simplerest.services.BuildingRecordServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest
//@RunWith(SpringRunner.class)
@RunWith(SpringRunner.class)
@WebMvcTest(BuildingRecordController.class)
public class BuildingRecordControllerTestM {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BuildingRecordService service;

    @Test
    public void getAllRecords() throws Exception {
        given(service.findAllRecords()).willReturn(
                Arrays.asList(new BuildingRecordResponse(1,"Vilnius", 2.0, 3.0,
                                new OwnerResponse(1, "Anna"), new PropertyResponse(1,"Flat",3.0)),
                        new BuildingRecordResponse(2,"Kaunas", 4.0, 5.0,
                                new OwnerResponse(20,"Tom"), new PropertyResponse(2,"House",6.0))
                ));

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/records")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"address\":\"Vilnius\",\"size\":2.0,\"value\":3.0,\"owner\":{\"name\":\"Anna\"},\"property\":{\"type\":\"Flat\",\"tax_rate\":3.0}},{\"address\":\"Kaunas\",\"size\":4.0,\"value\":5.0,\"owner\":{\"name\":\"Tom\"},\"property\":{\"type\":\"House\",\"tax_rate\":6.0}}]"))
                .andReturn();
    }

}
