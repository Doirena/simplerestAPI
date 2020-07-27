package com.dovile.simplerest.services;


import com.dovile.simplerest.domain.request.PropertyRequest;
import com.dovile.simplerest.domain.response.PropertyResponse;

import com.dovile.simplerest.entities.PropertyEntity;
import com.dovile.simplerest.exception.ResourceNotFoundException;
import com.dovile.simplerest.repositories.PropertyRespository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import static org.assertj.core.api.Assertions.assertThat;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PropertyServiceImplTest {

    @InjectMocks
    private PropertyServiceImpl propertyServiceImpl;

    @Mock
    private PropertyRespository propertyRespository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllProperties_test() {
        List<PropertyEntity> propertyEList = new ArrayList<PropertyEntity>();
        propertyEList.add(new PropertyEntity(1, "Flat", 20));
        propertyEList.add(new PropertyEntity(2, "House", 60));
        propertyEList.add(new PropertyEntity(3, "Garden House", 40));

        when(propertyRespository.findAll()).thenReturn(propertyEList);

        List<PropertyResponse> propertyRList = propertyServiceImpl.findAllProperties();

        assertEquals(3, propertyRList.size());
        verify(propertyRespository, times(1)).findAll();
        assertEquals(propertyEList.get(1).getType(), propertyRList.get(1).getType());
    }

    @Test
    public void findPropertyById_test() throws ResourceNotFoundException {
        Integer id = 1;
        PropertyEntity propertyE = new PropertyEntity(id, "Flat", 20);
        when(propertyRespository.findById(id)).thenReturn(java.util.Optional.of(propertyE));

        PropertyResponse propertyR = propertyServiceImpl.findPropertyById(id);
        assertEquals(propertyE.getType(), propertyR.getType());
    }

    @Test
    public void createProperty_test() {
        PropertyEntity propertyE = new PropertyEntity(null, "Flat", 20);
        given(propertyRespository.save(propertyE)).willAnswer(invocation -> invocation.getArgument(1));

        PropertyResponse propertyR = propertyServiceImpl.createProperty(new PropertyRequest("Flat", 20));

        assertThat(propertyR.getType()).isNotNull();
        verify(propertyRespository).save(any(PropertyEntity.class));
    }

    @Test
    public void refurbishProperty_test() throws ResourceNotFoundException {
        Integer id = 1;
        PropertyEntity propertyE = new PropertyEntity(id, "Flat", 20);
        when(propertyRespository.findById(id)).thenReturn(java.util.Optional.of(propertyE));
        propertyE.setType("House");
        when(propertyRespository.save(propertyE)).thenReturn(propertyE);

        PropertyResponse propertyR = propertyServiceImpl.refurbishProperty(id, new PropertyRequest("House", 20));

        assertThat(propertyR).isNotNull();
        assertEquals(propertyE.getType(), propertyR.getType());
    }

    @Test
    public void deleteProperty_test() throws Exception {
        //Given
        Integer id = 1;
        PropertyEntity propertyE = new PropertyEntity(id, "Flat", 20);

        when(propertyRespository.findById(id)).thenReturn(java.util.Optional.of(propertyE));

        final Map<String, Boolean> result = propertyServiceImpl.deleteProperty(id);

        verify(propertyRespository, times(1)).delete(propertyE);
        assertEquals(true, result.containsKey("deleted"));
    }
}