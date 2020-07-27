package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.request.OwnerRequest;
import com.dovile.simplerest.domain.response.OwnerResponse;
import com.dovile.simplerest.entities.OwnerEntity;
import com.dovile.simplerest.exception.ResourceNotFoundException;
import com.dovile.simplerest.repositories.OwnerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;


import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceImplTest {
    @InjectMocks
    private OwnerServiceImpl ownerServiceImpl;
    @Mock
    private OwnerRepository ownerRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllOwners_test() {
        List<OwnerEntity> listOwner = new ArrayList<OwnerEntity>();
        listOwner.add(new OwnerEntity(1, "Anna"));
        listOwner.add(new OwnerEntity(2, "Tom"));
        listOwner.add(new OwnerEntity(3, "John"));


        when(ownerRepository.findAll()).thenReturn(listOwner);

        List<OwnerResponse> expectedOwnerList = ownerServiceImpl.findAllOwners();

        assertEquals(3, expectedOwnerList.size());

        assertEquals(listOwner.get(0).getName(), expectedOwnerList.get(0).getName());
        assertEquals(listOwner.get(1).getName(), expectedOwnerList.get(1).getName());
        assertEquals(listOwner.get(2).getName(), expectedOwnerList.get(2).getName());
    }

    @Test
    public void findOwnerById_test() throws ResourceNotFoundException {
        Integer id = 1;
        OwnerEntity ownerE = new OwnerEntity(id, "Anna");

        when(ownerRepository.findById(id)).thenReturn(java.util.Optional.of(ownerE));

        OwnerResponse ownerResponse = ownerServiceImpl.findOwnerById(id);

        assertEquals(ownerE.getName(), ownerResponse.getName());
    }

    @Test
    public void findOwnerById_Bad_Request_test() {
        Integer id = 1;
        try {
            OwnerResponse ownerResponse = ownerServiceImpl.findOwnerById(id);
        } catch (ResourceNotFoundException e) {
            assertEquals("Owner not found on: " + id, e.getMessage());
        }
    }

    @Test
    public void createOwner_test() {
        OwnerEntity ownerE = new OwnerEntity(null, "Anna");
        given(ownerRepository.save(ownerE)).willAnswer(invocation -> invocation.getArgument(1));

        OwnerResponse ownerResponse = ownerServiceImpl.createOwner(new OwnerRequest("Anna"));

        assertThat(ownerResponse).isNotNull();
        verify(ownerRepository).save(any(OwnerEntity.class));
    }

    @Test
    public void deleteOwnerById_test() throws Exception {
        //Given
        Integer id = 1;
        OwnerEntity ownerE = new OwnerEntity(id, "Anna");

        when(ownerRepository.findById(id)).thenReturn(java.util.Optional.of(ownerE));

        // when
        final Map<String, Boolean> result = ownerServiceImpl.deleteOwnerById(id);

        // then
        verify(ownerRepository, times(1)).delete(ownerE);
        assertEquals(true, result.containsKey("deleted"));
    }

    @Test
    public void refurbishOwner_test() throws ResourceNotFoundException {
        Integer id = 1;
        OwnerEntity ownerE = new OwnerEntity(id, "Anna");
        when(ownerRepository.findById(id)).thenReturn(java.util.Optional.of(ownerE));
        ownerE.setName("Tom");
        when(ownerRepository.save(ownerE)).thenReturn(ownerE);

        OwnerResponse ownerResponse = ownerServiceImpl.refurbishOwner(1, new OwnerRequest("Tom"));

        //then
        assertThat(ownerResponse).isNotNull();
        assertEquals(ownerE.getName(), ownerResponse.getName());
    }
}