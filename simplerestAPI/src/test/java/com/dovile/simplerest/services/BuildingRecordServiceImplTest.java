package com.dovile.simplerest.services;

import com.dovile.simplerest.domain.request.BuildingRecordsRequest;
import com.dovile.simplerest.domain.response.BuildingRecordResponse;
import com.dovile.simplerest.entities.BuildingRecordEntity;
import com.dovile.simplerest.entities.OwnerEntity;
import com.dovile.simplerest.entities.PropertyEntity;
import com.dovile.simplerest.exception.ResourceNotFoundException;
import com.dovile.simplerest.repositories.BuildingRecordRepository;
import com.dovile.simplerest.repositories.OwnerRepository;
import com.dovile.simplerest.repositories.PropertyRespository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BuildingRecordServiceImplTest {

    @InjectMocks
    private BuildingRecordServiceImpl buildingRecordServiceImpl;

    @Mock
    private BuildingRecordRepository buildingRecordRepository;

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private PropertyRespository propertyRespository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllRecords_test() {
        List<BuildingRecordEntity> recordEList = new ArrayList<BuildingRecordEntity>();
        recordEList.add(new BuildingRecordEntity(1, "Laisves pr. Vilnius", 80, 1.5,
                new OwnerEntity(1, "Anna"),
                new PropertyEntity(1, "Flat", 2)));
        recordEList.add(new BuildingRecordEntity(2, "Laisves pr. Kaunas", 90, 1,
                new OwnerEntity(2, "Tom"),
                new PropertyEntity(2, "House", 4)));
        recordEList.add(new BuildingRecordEntity(3, "Gedimino pr. Vilnius", 170, 2,
                new OwnerEntity(3, "John"),
                new PropertyEntity(3, "Apartament", 5)));

        when(buildingRecordRepository.findAll()).thenReturn(recordEList);

        List<BuildingRecordResponse> buildingRecordRList = buildingRecordServiceImpl.findAllRecords();

        assertEquals(3, buildingRecordRList.size());
    }

    @Test
    public void createRecord_test() {
        BuildingRecordEntity buildingRecordE = new BuildingRecordEntity(null,"Laisves pr. Vilnius", 80, 1.5);

        OwnerEntity ownerE = new OwnerEntity(1, "Anna");
        when(ownerRepository.findBYName("Anna")).thenReturn(ownerE);
        PropertyEntity propertyE = new PropertyEntity(1, "Flat", 20);
        when(propertyRespository.findBYType("Flat")).thenReturn(propertyE);

        buildingRecordE.setOwner(ownerE);
        buildingRecordE.setPropertyType(propertyE);
        when(buildingRecordRepository.save(buildingRecordE)).thenAnswer(invocation -> invocation.getArgument(1));

        BuildingRecordResponse buildingRecordR = buildingRecordServiceImpl.createRecord(
                new BuildingRecordsRequest("Laisves pr. Vilnius", 80, 1.5), "Anna111", "Flat");

        assertThat(buildingRecordR).isNotNull();
        assertEquals(buildingRecordE.getOwner().getName(), buildingRecordR.getOwner().getName());
    }

    @Test
    public void refurbishRecord_test() throws ResourceNotFoundException {
        Integer id = 1;
        BuildingRecordEntity buildingRecordE = new BuildingRecordEntity(1, "Laisves pr. Vilnius", 80, 1.5,
                new OwnerEntity(id, "Anna"),
                new PropertyEntity(id, "Flat", 2));

        when(buildingRecordRepository.findById(id)).thenReturn(java.util.Optional.of(buildingRecordE));
        buildingRecordE.setAddress("Kauno g.Vilnius");
        when(buildingRecordRepository.save(buildingRecordE)).thenReturn(buildingRecordE);

        BuildingRecordResponse buildingRecordR = buildingRecordServiceImpl.refurbishRecord(id, new BuildingRecordsRequest("Kauno g.Vilnius", 80, 1.5));

        assertThat(buildingRecordR).isNotNull();
        assertEquals(buildingRecordE.getOwner().getName(), buildingRecordR.getOwner().getName());
    }

    @Test
    public void deleteRecord_test() throws Exception {
        Integer id = 1;
        BuildingRecordEntity buildingRecordE = new BuildingRecordEntity(1, "Laisves pr. Vilnius", 80, 1.5,
                new OwnerEntity(id, "Anna"),
                new PropertyEntity(id, "Flat", 2));

        when(buildingRecordRepository.findById(id)).thenReturn(java.util.Optional.of(buildingRecordE));

        final Map<String, Boolean> result = buildingRecordServiceImpl.deleteRecord(id);

        verify(buildingRecordRepository, times(1)).delete(buildingRecordE);
        assertEquals(true, result.containsKey("deleted"));
    }

    @Test
    public void calculateTaxesByOwnerId_test() {
        Integer id = 1;
        when(buildingRecordRepository.RealEstateTaxes(id)).thenReturn("3.5");

        String result = buildingRecordServiceImpl.calculateTaxesByOwnerId(id);

        assertEquals("3.5", result);
    }

    @Test
    public void getOneRecord_test() throws ResourceNotFoundException {
        Integer id = 1;
        BuildingRecordEntity buildingRecordE = new BuildingRecordEntity(1, "Laisves pr. Vilnius", 80, 1.5,
                new OwnerEntity(1, "Anna"),
                new PropertyEntity(1, "Flat", 2));

        when(buildingRecordRepository.findById(id)).thenReturn(java.util.Optional.of(buildingRecordE));

        BuildingRecordResponse buildingRecordR = buildingRecordServiceImpl.getOneRecord(id);

        assertEquals(buildingRecordE.getOwner().getName(), buildingRecordR.getOwner().getName());
    }

    @Test
    public void createRecord_getOwner_ArithmeticException_test() {
        BuildingRecordEntity buildingRecordE = new BuildingRecordEntity(null,"Laisves pr. Vilnius", 80, 1.5);

        OwnerEntity ownerE = new OwnerEntity(1, "Anna");
        when(ownerRepository.findBYName("Anna")).thenReturn(ownerE);
        PropertyEntity propertyE = new PropertyEntity(1, "Flat", 20);
        when(propertyRespository.findBYType("Flat")).thenReturn(propertyE);

        buildingRecordE.setOwner(ownerE);
        buildingRecordE.setPropertyType(propertyE);
        try {
            BuildingRecordResponse buildingRecordR = buildingRecordServiceImpl.createRecord(
                    new BuildingRecordsRequest("Laisves pr. Vilnius", 80, 1.5), "Anna111", "Flat");
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());
            assertEquals("Please insert a correct Owner", e.getMessage());
        }
    }

    @Test
    public void createRecord_getPropertyType_ArithmeticException_test() {
        BuildingRecordEntity buildingRecordE = new BuildingRecordEntity(null,"Laisves pr. Vilnius", 80, 1.5);

        OwnerEntity ownerE = new OwnerEntity(1, "Anna");
        when(ownerRepository.findBYName("Anna")).thenReturn(ownerE);
        PropertyEntity propertyE = new PropertyEntity(1, "Flat", 20);
        when(propertyRespository.findBYType("Flat")).thenReturn(propertyE);

        buildingRecordE.setOwner(ownerE);
        buildingRecordE.setPropertyType(propertyE);
        try {
            BuildingRecordResponse buildingRecordR = buildingRecordServiceImpl.createRecord(
                    new BuildingRecordsRequest("Laisves pr. Vilnius", 80, 1.5), "Anna", "Flat111");
        } catch (ArithmeticException e){
            System.out.println(e.getMessage());
            assertEquals("Please insert a correct Property Type", e.getMessage());
        }
    }
}